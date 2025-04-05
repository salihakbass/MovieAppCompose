package com.salihakbas.movieappcompose.ui.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
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
    navigateToDetail: (Int) -> Unit
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> ExploreContent(
            uiState = uiState,
            onAction = onAction,
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
fun ExploreContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            OutlinedTextField(
                value = uiState.query,
                onValueChange = { onAction(UiAction.OnQueryChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text(text = "Search for movies", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onAction(UiAction.OnSearch)
                    }
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(R.color.main_blue_bg),
                    unfocusedContainerColor = colorResource(R.color.main_blue_bg),
                    focusedIndicatorColor = colorResource(R.color.white),
                    unfocusedIndicatorColor = colorResource(R.color.white),
                )
            )
        }

        when {
            !uiState.hasSearched -> {
                item {
                    SuggestedMovies(uiState = uiState, navigateToDetail = navigateToDetail)
                }
            }

            uiState.hasSearched && uiState.movies.isEmpty() -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 64.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.notfound),
                            contentDescription = "Not Found",
                            modifier = Modifier.size(200.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Sorry, your search was not found",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            uiState.movies.isNotEmpty() -> {

                item {
                    Text(
                        text = "Your Search Result",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }

                item {
                    LazyRow {
                        items(uiState.movies) { movie ->
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                                contentDescription = null,
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(8.dp)
                                    .clip(shape = RoundedCornerShape(24.dp))
                                    .clickable {
                                        navigateToDetail(movie.id)
                                    },
                                alignment = Alignment.Center,
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }

                listOf(
                    "You May Like This",
                    "Still Looking? Try These!",
                    "Hidden Gems You Shouldn't Miss",
                    "Trending Now"
                ).forEach { title ->
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    item {
                        MoviesRow(uiState = uiState, navigateToDetail = navigateToDetail)
                    }
                }
            }
        }
    }
}

@Composable
fun MoviesRow(
    uiState: UiState,
    navigateToDetail: (Int) -> Unit
) {
    val shuffledList = uiState.suggestedMovies.shuffled()
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(shuffledList) { movie ->
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .clickable {
                        navigateToDetail(movie.id)
                    },
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
fun SuggestedMovies(
    uiState: UiState,
    navigateToDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Suggested for You",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            uiState.suggestedMovies.forEach { movie ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1E2A38), shape = RoundedCornerShape(12.dp))
                        .padding(12.dp)
                        .clickable {
                            navigateToDetail(movie.id)
                        }
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                        contentDescription = movie.title,
                        modifier = Modifier
                            .size(width = 100.dp, height = 150.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = movie.title,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = movie.overview,
                            color = Color.LightGray,
                            fontSize = 14.sp,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_star),
                                contentDescription = null,
                                tint = colorResource(R.color.main_orange),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = String.format("%.1f", movie.vote_average),
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
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
        navigateToDetail = {}
    )
}