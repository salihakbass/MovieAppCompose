package com.salihakbas.movieappcompose.ui.profile

object ProfileContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect {
        data object NavigateToSubscribe : UiEffect()
    }
}