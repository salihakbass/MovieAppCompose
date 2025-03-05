package com.salihakbas.movieappcompose.ui.onboarding

object OnboardingContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect
}