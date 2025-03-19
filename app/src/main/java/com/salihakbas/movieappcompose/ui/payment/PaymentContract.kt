package com.salihakbas.movieappcompose.ui.payment

object PaymentContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect {
        data object NavigateToBack : UiEffect()
    }
}