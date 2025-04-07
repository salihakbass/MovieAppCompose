package com.salihakbas.movieappcompose.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salihakbas.movieappcompose.common.Resource
import com.salihakbas.movieappcompose.domain.repository.FirebaseAuthRepository
import com.salihakbas.movieappcompose.ui.signin.SignInContract.UiAction
import com.salihakbas.movieappcompose.ui.signin.SignInContract.UiEffect
import com.salihakbas.movieappcompose.ui.signin.SignInContract.UiState
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
class SignInViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.EmailChanged -> updateUiState { copy(email = uiAction.email) }
            is UiAction.PasswordChanged -> updateUiState { copy(password = uiAction.password) }
            is UiAction.SignInClicked -> signIn()
        }
    }

    private fun signIn() = viewModelScope.launch {
        when (
            val result = authRepository.signInWithEmailAndPassword(
                email = uiState.value.email,
                password = uiState.value.password
            )
        ) {
            is Resource.Success -> {
                emitUiEffect(UiEffect.NavigateToHome)
            }

            is Resource.Error -> {
                // Handle error
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