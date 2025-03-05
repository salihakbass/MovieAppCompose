package com.salihakbas.movieappcompose.ui.notification

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NotificationScreenPreviewProvider : PreviewParameterProvider<NotificationContract.UiState> {
    override val values: Sequence<NotificationContract.UiState>
        get() = sequenceOf(
            NotificationContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            NotificationContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            NotificationContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}