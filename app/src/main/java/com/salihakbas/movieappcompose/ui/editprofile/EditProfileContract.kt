package com.salihakbas.movieappcompose.ui.editprofile

object EditProfileContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect
}