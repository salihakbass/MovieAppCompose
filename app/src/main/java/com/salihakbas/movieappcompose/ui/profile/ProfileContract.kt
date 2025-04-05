package com.salihakbas.movieappcompose.ui.profile

object ProfileContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val name: String = "",
        val surname: String = ""
    )

    sealed class UiAction

    sealed class UiEffect {
        data object NavigateToSubscribe : UiEffect()
        data object NavigateToEditProfile: UiEffect()
    }
}