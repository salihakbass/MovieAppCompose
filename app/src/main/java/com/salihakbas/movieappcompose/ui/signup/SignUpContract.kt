package com.salihakbas.movieappcompose.ui.signup

object SignUpContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val email: String = "",
        val password: String = "",
        val nameSurname: String = "",
        val phoneNumber: String = "",
        val nameSurnameError: String? = null,
        val emailError: String? = null,
        val phoneNumberError: String? = null,
        val passwordError: String? = null
    )

    sealed class UiAction {
        data object SignUpClicked : UiAction()
        data class OnNameSurnameChanged(val nameSurname: String) : UiAction()
        data class OnPhoneNumberChanged(val phoneNumber: String) : UiAction()
        data class OnEmailChanged(val email: String) : UiAction()
        data class OnPasswordChanged(val password: String) : UiAction()
    }

    sealed class UiEffect {
        data object NavigateToSignIn : UiEffect()
        data object ShowSignUpToast : UiEffect()
    }
}