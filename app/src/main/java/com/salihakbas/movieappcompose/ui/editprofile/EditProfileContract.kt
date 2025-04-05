package com.salihakbas.movieappcompose.ui.editprofile

object EditProfileContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val phoneNumber: String = "",
    )

    sealed class UiAction

    sealed class UiEffect
}