package com.salihakbas.movieappcompose.ui.detail

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.common.collectWithLifecycle
import com.salihakbas.movieappcompose.data.model.Cast
import com.salihakbas.movieappcompose.data.model.Crew
import com.salihakbas.movieappcompose.data.model.Genre
import com.salihakbas.movieappcompose.data.model.Movie
import com.salihakbas.movieappcompose.data.model.MovieCreditsResponse
import com.salihakbas.movieappcompose.data.model.MovieDetailResponse
import com.salihakbas.movieappcompose.data.model.Series
import com.salihakbas.movieappcompose.data.model.SeriesCast
import com.salihakbas.movieappcompose.data.model.SeriesCreditsResponse
import com.salihakbas.movieappcompose.data.model.SeriesCrew
import com.salihakbas.movieappcompose.data.model.TvShowResponse
import com.salihakbas.movieappcompose.ui.components.CircleBackgroundIcon
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.detail.DetailContract.UiAction
import com.salihakbas.movieappcompose.ui.detail.DetailContract.UiEffect
import com.salihakbas.movieappcompose.ui.detail.DetailContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.util.Locale

@Composable
fun DetailScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navigateBack: () -> Unit,
    navigateToTrailer: (Int) -> Unit,
    navigateToPersonDetail: (Int) -> Unit,
    navigateToMovieDetail: (Int) -> Unit,
    navigateToSeriesDetail: (Int) -> Unit
) {
    uiEffect.collectWithLifecycle {
        when (it) {
            is UiEffect.NavigateBack -> navigateBack()
            is UiEffect.NavigateToTrailer -> navigateToTrailer(it.movieId)
        }
    }
    when {
        uiState.isLoading -> LoadingBar()
        uiState.movie != null -> {
            MovieDetailContent(
                movie = uiState.movie,
                uiState.movieCredit,
                navigateBack,
                navigateToTrailer,
                navigateToPersonDetail,
                uiState,
                navigateToMovieDetail
            )
        }

        uiState.series != null -> {
            SeriesDetailContent(
                series = uiState.series,
                uiState.seriesCredit,
                navigateBack,
                navigateToPersonDetail,
                uiState,
                navigateToSeriesDetail
            )
        }

        else -> {
            EmptyScreen()
        }
    }
}

@Composable
fun MovieDetailContent(
    movie: MovieDetailResponse,
    credits: MovieCreditsResponse?,
    navigateBack: () -> Unit,
    navigateToTrailer: (Int) -> Unit,
    navigateToPersonDetail: (Int) -> Unit,
    uiState: UiState,
    navigateToMovieDetail: (Int) -> Unit
) {
    val runtimeInMinutes = movie.runtime
    val hours = runtimeInMinutes / 60
    val minutes = runtimeInMinutes % 60
    val formattedRuntime = "${if (hours > 0) "${hours}h " else ""}${minutes}min"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(navigateBack, uiState)
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = movie.title,
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f, false)
            )
            Text(
                text = "(${movie.release_date.substring(0, 4)})",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = formattedRuntime,
                    color = Color.White,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = null,
                        tint = colorResource(R.color.main_orange),
                        modifier = Modifier.size(28.dp)
                    )
                    Text(
                        text = String.format(Locale.US, "%.1f", movie.vote_average),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                GenreRow(movie.genres)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Button(
            onClick = { navigateToTrailer(movie.id) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.main_orange)
            )
        ) {
            Text(text = "Play Trailer", color = Color.Black, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.overview,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        credits?.cast?.let {
            MovieCreditsSection(it, credits.crew, navigateToPersonDetail)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Similar Movies",
            color = Color.White,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(uiState.similarMovies) { movie ->
                SimilarMovieItem(movie, navigateToMovieDetail)
            }
        }
    }
}

@Composable
fun SeriesDetailContent(
    series: TvShowResponse,
    credits: SeriesCreditsResponse?,
    navigateBack: () -> Unit,
    navigateToPersonDetail: (Int) -> Unit,
    uiState: UiState,
    navigateToSeriesDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(navigateBack, uiState)
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${series.poster_path}",
            contentDescription = series.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = series.name ?: "Unknown Series",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "(${series.first_air_date?.substring(0, 4)})",
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = colorResource(R.color.main_orange),
                modifier = Modifier.size(28.dp)
            )
            Text(
                text = String.format(Locale.US, "%.1f", series.vote_average),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        GenreRow(series.genres)

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.main_orange)
            )
        ) {
            Text(text = "Play Trailer", color = Color.Black, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = series.overview ?: "No overview available",
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        SeriesCreditsSection(
            credits?.cast ?: emptyList(),
            credits?.crew ?: emptyList(),
            navigateToPersonDetail
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Similar Series",
            color = Color.White,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(uiState.similarSeries) { series ->
                SimilarSeriesItem(series, navigateToSeriesDetail)
            }
        }
    }
}

@Composable
fun TopBar(
    onClick: () -> Unit,
    uiState: UiState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleBackgroundIcon(onClick = { onClick() })
        Icon(
            painter = painterResource(R.drawable.ic_bookmark),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp),
            tint = colorResource(R.color.main_orange)
        )
    }
}

@Composable
fun MovieCreditsSection(
    castList: List<Cast>,
    crewList: List<Crew>,
    navigateToPersonDetail: (Int) -> Unit
) {
    Column {
        Text(
            text = "Cast",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(castList) { cast ->
                Column(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(end = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${cast.profile_path}",
                        contentDescription = cast.name,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .clickable {
                                navigateToPersonDetail(cast.id)
                            },
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = cast.name,
                        color = Color.White,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Directors",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(crewList) { crew ->
                Column(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(end = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${crew.profile_path}",
                        contentDescription = crew.name,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .clickable {
                                navigateToPersonDetail(crew.id)
                            },
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = crew.name,
                        color = Color.White,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2
                    )
                }
            }
        }
    }
}

@Composable
fun SeriesCreditsSection(
    castList: List<SeriesCast>,
    crewList: List<SeriesCrew>,
    navigateToPersonDetail: (Int) -> Unit
) {
    Column {
        Text(
            text = "Cast",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(castList) { cast ->
                Column(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(end = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${cast.profile_path}",
                        contentDescription = cast.name,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .clickable {
                                navigateToPersonDetail(cast.id)
                            },
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    cast.name?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 2
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Directors",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(crewList) { crew ->
                Column(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(end = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${crew.profile_path}",
                        contentDescription = crew.name,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .clickable {
                                navigateToPersonDetail(crew.id)
                            },
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    crew.name?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 2
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GenreRow(genres: List<Genre>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(genres) { genre ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dot),
                    contentDescription = null,
                    tint = colorResource(R.color.main_orange),
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = genre.name,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun SimilarMovieItem(
    movie: Movie,
    navigateToMovieDetail: (Int) -> Unit
) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
        contentDescription = movie.title,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { navigateToMovieDetail(movie.id) },
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun SimilarSeriesItem(
    series: Series,
    navigateToSeriesDetail: (Int) -> Unit
) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500${series.poster_path}",
        contentDescription = series.name,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { navigateToSeriesDetail(series.id) },
        contentScale = ContentScale.FillBounds
    )
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(
    @PreviewParameter(DetailScreenPreviewProvider::class) uiState: UiState,
) {
    DetailScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        navigateBack = {},
        navigateToTrailer = {},
        navigateToPersonDetail = {},
        navigateToMovieDetail = {},
        navigateToSeriesDetail = {}
    )
}