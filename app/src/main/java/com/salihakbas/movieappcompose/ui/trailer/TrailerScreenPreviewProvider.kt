package com.salihakbas.movieappcompose.ui.trailer

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class TrailerScreenPreviewProvider : PreviewParameterProvider<TrailerContract.UiState> {
    override val values: Sequence<TrailerContract.UiState>
        get() = sequenceOf(
            TrailerContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            TrailerContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            TrailerContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}