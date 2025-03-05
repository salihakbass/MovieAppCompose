package com.salihakbas.movieappcompose.ui.explore

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ExploreScreenPreviewProvider : PreviewParameterProvider<ExploreContract.UiState> {
    override val values: Sequence<ExploreContract.UiState>
        get() = sequenceOf(
            ExploreContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            ExploreContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            ExploreContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}