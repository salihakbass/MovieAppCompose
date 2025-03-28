package com.salihakbas.movieappcompose.ui.subscribe

object SubscribeContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect {
        data object NavigateToPayment : UiEffect()
        data object NavigateToBack : UiEffect()
    }
}