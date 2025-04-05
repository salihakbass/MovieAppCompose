package com.salihakbas.movieappcompose.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import com.salihakbas.movieappcompose.common.Resource
import com.salihakbas.movieappcompose.domain.repository.FirebaseAuthRepository
import com.salihakbas.movieappcompose.ui.signup.SignUpContract.UiAction
import com.salihakbas.movieappcompose.ui.signup.SignUpContract.UiEffect
import com.salihakbas.movieappcompose.ui.signup.SignUpContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.SignUpClicked -> signUp()
            is UiAction.OnNameSurnameChanged -> updateUiState { copy(nameSurname = uiAction.nameSurname) }
            is UiAction.OnPhoneNumberChanged -> updateUiState { copy(phoneNumber = uiAction.phoneNumber) }
            is UiAction.OnEmailChanged -> updateUiState { copy(email = uiAction.email) }
            is UiAction.OnPasswordChanged -> updateUiState { copy(password = uiAction.password) }
        }
    }

    private fun signUp() = viewModelScope.launch {
        if (!validateFields()) return@launch

        val state = uiState.value

        when (val result = authRepository.createUserWithEmailAndPassword(
            nameSurname = state.nameSurname,
            phoneNumber = state.phoneNumber,
            email = state.email,
            password = state.password
        )) {
            is Resource.Success -> {
                saveUserToRealtimeDatabase(
                    result.data,
                    state.nameSurname,
                    state.email,
                    state.phoneNumber
                )
                emitUiEffect(UiEffect.NavigateToSignIn)
            }

            is Resource.Error -> {

            }
        }
    }

    private fun saveUserToRealtimeDatabase(
        userId: String,
        name: String,
        email: String,
        phone: String
    ) {
        val database = FirebaseDatabase.getInstance().reference
        val userMap = mapOf(
            "name" to name,
            "email" to email,
            "phone" to phone
        )
        database.child("users").child(userId).setValue(userMap)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
            }
    }

    private fun validateFields(): Boolean {
        val state = uiState.value

        val nameError = if (state.nameSurname.isBlank()) "Bu alan boş bırakılamaz" else null
        val emailError = if (state.email.isBlank()) "Bu alan boş bırakılamaz" else null
        val phoneError = if (state.phoneNumber.isBlank()) "Bu alan boş bırakılamaz" else null
        val passwordError = if (state.password.isBlank()) "Bu alan boş bırakılamaz" else null

        updateUiState {
            copy(
                nameSurnameError = nameError,
                emailError = emailError,
                phoneNumberError = phoneError,
                passwordError = passwordError
            )
        }

        return listOf(nameError, emailError, phoneError, passwordError).all { it == null }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}