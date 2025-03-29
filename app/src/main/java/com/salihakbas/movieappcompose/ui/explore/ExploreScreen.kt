package com.salihakbas.movieappcompose.ui.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.explore.ExploreContract.UiAction
import com.salihakbas.movieappcompose.ui.explore.ExploreContract.UiEffect
import com.salihakbas.movieappcompose.ui.explore.ExploreContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun ExploreScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> ExploreContent(
            uiState = uiState,
            onAction = onAction
        )
    }
}

@Composable
fun ExploreContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = uiState.query,
            onValueChange = { onAction(UiAction.OnQueryChanged(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text(text = "Search for movies", color = Color.White) },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.main_blue_bg),
                unfocusedContainerColor = colorResource(R.color.main_blue_bg),
                focusedIndicatorColor = colorResource(R.color.white),
                unfocusedIndicatorColor = colorResource(R.color.white),
            )

        )
        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.movies.isEmpty()) {
            EmptyScreen()
        } else {
            Text(
                text = "Your Search Result",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp)
            )
            LazyRow {
                items(uiState.movies) { movie ->
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .padding(8.dp)
                            .clip(shape = RoundedCornerShape(24.dp)),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview(
    @PreviewParameter(ExploreScreenPreviewProvider::class) uiState: UiState,
) {
    ExploreScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
    )
}