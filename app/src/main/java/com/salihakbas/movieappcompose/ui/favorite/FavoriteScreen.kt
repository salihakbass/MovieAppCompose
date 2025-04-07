package com.salihakbas.movieappcompose.ui.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.favorite.FavoriteContract.UiAction
import com.salihakbas.movieappcompose.ui.favorite.FavoriteContract.UiEffect
import com.salihakbas.movieappcompose.ui.favorite.FavoriteContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun FavoriteScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> FavoriteContent()
    }
}

@Composable
fun FavoriteContent() {
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview(
    @PreviewParameter(FavoriteScreenPreviewProvider::class) uiState: UiState,
) {
    FavoriteScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
    )
}