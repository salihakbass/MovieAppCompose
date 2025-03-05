package com.salihakbas.movieappcompose.ui.subscribe

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class SubscribeScreenPreviewProvider : PreviewParameterProvider<SubscribeContract.UiState> {
    override val values: Sequence<SubscribeContract.UiState>
        get() = sequenceOf(
            SubscribeContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            SubscribeContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            SubscribeContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}