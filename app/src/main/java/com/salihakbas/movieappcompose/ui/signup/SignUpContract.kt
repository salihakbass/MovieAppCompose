package com.salihakbas.movieappcompose.ui.signup

object SignUpContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val email: String = "",
        val password: String = "",
        val nameSurname: String = "",
        val phoneNumber: String = "",
        val errorState: ErrorState? = null,
    ) {
        fun checkError(): UiState {
            return copy(
                errorState = ErrorState(
                    nameSurnameError = nameSurname.isEmpty(),
                    phoneNumberError = phoneNumber.isEmpty(),
                    emailError = email.isEmpty(),
                    passwordError = password.isEmpty()
                )
            )
        }

    }

    sealed class UiAction {
        data object SignUpClicked : UiAction()
        data class OnNameSurnameChanged(val nameSurname: String) : UiAction()
        data class OnPhoneNumberChanged(val phoneNumber: String) : UiAction()
        data class OnEmailChanged(val email: String) : UiAction()
        data class OnPasswordChanged(val password: String) : UiAction()
    }

    data class ErrorState(
        val nameSurnameError: Boolean = false,
        val phoneNumberError: Boolean = false,
        val emailError: Boolean = false,
        val passwordError: Boolean = false,
    ) {
        fun hasError(): Boolean {
            return nameSurnameError || phoneNumberError || emailError || passwordError
        }
    }

    sealed class UiEffect {
        data object NavigateToSignIn : UiEffect()
        data object ShowSignUpToast : UiEffect()
    }
}