package com.salihakbas.movieappcompose.ui.trailer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.salihakbas.movieappcompose.ui.components.CircleBackgroundIcon
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.trailer.TrailerContract.UiAction
import com.salihakbas.movieappcompose.ui.trailer.TrailerContract.UiEffect
import com.salihakbas.movieappcompose.ui.trailer.TrailerContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun TrailerScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onBackClick: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        onDispose {

        }
    }
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> uiState.videoKey?.let {
            TrailerContent(
                videoKey = it
            )
        }
    }
}

@Composable
fun TrailerContent(videoKey: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CircleBackgroundIcon(
                onClick = {}
            )
        }
        val lifecycleOwner = LocalLifecycleOwner.current

        AndroidView(
            factory = { context ->
                val youTubePlayerView = YouTubePlayerView(context).apply {
                    enableAutomaticInitialization = false
                    initialize(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.cueVideo(videoKey, 0f)
                        }
                    })
                }

                lifecycleOwner.lifecycle.addObserver(youTubePlayerView)

                youTubePlayerView
            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .padding(16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun TrailerScreenPreview(
    @PreviewParameter(TrailerScreenPreviewProvider::class) uiState: UiState,
) {
    TrailerScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        onBackClick = {}
    )
}