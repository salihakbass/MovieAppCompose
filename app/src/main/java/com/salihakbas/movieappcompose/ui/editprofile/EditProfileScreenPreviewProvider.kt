package com.salihakbas.movieappcompose.ui.editprofile

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.salihakbas.movieappcompose.ui.favorite.FavoriteContract

class FavoriteScreenPreviewProvider : PreviewParameterProvider<EditProfileContract.UiState> {
    override val values: Sequence<EditProfileContract.UiState>
        get() = sequenceOf(
            EditProfileContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            EditProfileContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            EditProfileContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}