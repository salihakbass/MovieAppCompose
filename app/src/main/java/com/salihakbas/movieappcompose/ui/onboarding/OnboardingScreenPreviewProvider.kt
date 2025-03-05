package com.salihakbas.movieappcompose.ui.onboarding

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class OnboardingScreenPreviewProvider : PreviewParameterProvider<OnboardingContract.UiState> {
    override val values: Sequence<OnboardingContract.UiState>
        get() = sequenceOf(
            OnboardingContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            OnboardingContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            OnboardingContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}