package com.salihakbas.movieappcompose.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _errorState = MutableStateFlow(SignUpContract.ErrorState())
    val errorState: StateFlow<SignUpContract.ErrorState> = _errorState.asStateFlow()

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
        updateUiState { checkError() }

        if (uiState.value.errorState?.hasError() == true) {
            emitUiEffect(UiEffect.ShowSignUpToast)
            return@launch
        }

        when (val result = authRepository.createUserWithEmailAndPassword(
            nameSurname = uiState.value.nameSurname,
            phoneNumber = uiState.value.phoneNumber,
            email = uiState.value.email,
            password = uiState.value.password

        )) {
            is Resource.Success -> {
                emitUiEffect(UiEffect.NavigateToSignIn)
            }

            is Resource.Error -> {

            }
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}