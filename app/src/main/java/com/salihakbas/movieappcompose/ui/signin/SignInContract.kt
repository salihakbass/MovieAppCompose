package com.salihakbas.movieappcompose.ui.signin

object SignInContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val email: String = "",
        val password: String = ""
    )

    sealed class UiAction {
        data class EmailChanged(val email: String) : UiAction()
        data class PasswordChanged(val password: String) : UiAction()
        data object SignInClicked : UiAction()
    }

    sealed class UiEffect {
        data object NavigateToSignUp : UiEffect()
        data object NavigateToHome : UiEffect()
    }
}