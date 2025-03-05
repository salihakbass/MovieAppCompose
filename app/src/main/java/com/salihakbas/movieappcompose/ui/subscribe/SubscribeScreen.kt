package com.salihakbas.movieappcompose.ui.subscribe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.subscribe.SubscribeContract.UiAction
import com.salihakbas.movieappcompose.ui.subscribe.SubscribeContract.UiEffect
import com.salihakbas.movieappcompose.ui.subscribe.SubscribeContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SubscribeScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> SubscribeContent()
    }
}

@Composable
fun SubscribeContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Subscribe Content",
            fontSize = 24.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SubscribeScreenPreview(
    @PreviewParameter(SubscribeScreenPreviewProvider::class) uiState: UiState,
) {
    SubscribeScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
    )
}