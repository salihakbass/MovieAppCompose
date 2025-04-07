package com.salihakbas.movieappcompose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.data.model.Movie
import com.salihakbas.movieappcompose.data.model.Series
import com.salihakbas.movieappcompose.ui.components.AvatarProfileWithPlaceholder
import com.salihakbas.movieappcompose.ui.components.DotIndicator
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.HomeTabRow
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.home.HomeContract.UiAction
import com.salihakbas.movieappcompose.ui.home.HomeContract.UiEffect
import com.salihakbas.movieappcompose.ui.home.HomeContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.util.Locale

@Composable
fun HomeScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navigateToMovieDetail: (Int) -> Unit,
    navigateToSeriesDetail: (Int) -> Unit
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> HomeContent(
            uiState = uiState,
            navigateToMovieDetail,
            navigateToSeriesDetail
        )
    }
}

@Composable
fun HomeContent(
    uiState: UiState,
    navigateToDetail: (Int) -> Unit,
    navigateToSeriesDetail: (Int) -> Unit
) {
    val upcomingMovies = uiState.upcomingMovieList.take(6)
    val airingTodaySeries = uiState.airingTodayTvSeriesList.take(6)
    val pagerStateMovies = rememberPagerState { upcomingMovies.size }
    val pagerStateSeries = rememberPagerState { airingTodaySeries.size }
    val tabs = listOf("Movies", "TV Series", "Animation")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TopBarHome(uiState = uiState)
            Spacer(modifier = Modifier.height(16.dp))
            HomeTabRow(
                tabTitles = tabs,
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index ->
                    selectedTabIndex = index
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            when (selectedTabIndex) {
                0 -> MovieSection(uiState, pagerStateMovies, upcomingMovies, navigateToDetail)
                1 -> SeriesSection(
                    uiState,
                    pagerStateSeries,
                    airingTodaySeries,
                    navigateToSeriesDetail
                )
            }
        }
    }
}

@Composable
fun SeriesSection(
    uiState: UiState,
    pagerState: PagerState,
    airingTodaySeries: List<Series>,
    navigateToSeriesDetail: (Int) -> Unit
) {
    Column {
        Text(
            text = "Airing Today",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) { page ->
                    SeriesImageItem(airingTodaySeries[page], navigateToSeriesDetail)
                }
                Spacer(modifier = Modifier.height(8.dp))

                DotIndicator(
                    totalDots = airingTodaySeries.size,
                    selectedIndex = pagerState.currentPage
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "On The Air",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(uiState.onTheAirSeriesList.shuffled()) { series ->
                SeriesItem(series, navigateToSeriesDetail)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Popular",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(uiState.popularSeriesList.shuffled()) { series ->
                SeriesItem(series, navigateToSeriesDetail)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Top Rated",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(uiState.topRatedSeriesList.shuffled()) { series ->
                SeriesItem(series, navigateToSeriesDetail)
            }
        }
    }
}

@Composable
fun SeriesItem(
    series: Series,
    navigateToSeriesDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                vertical = 8.dp,
                horizontal = 4.dp
            )
            .clickable {
                navigateToSeriesDetail(series.id)
            }
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${series.poster_path}",
            contentDescription = series.name,
            modifier = Modifier
                .size(170.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = series.name,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(120.dp),
            maxLines = 1
        )
        Row {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                tint = colorResource(R.color.main_orange),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = String.format(Locale.US, "%.1f", series.vote_average),
                color = Color.White
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "(${series.vote_count})",
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun MovieImageItem(
    movie: Movie,
    navigateToDetail: (Int) -> Unit
) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
        contentDescription = movie.title,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navigateToDetail(movie.id)
            },
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun SeriesImageItem(
    series: Series,
    navigateToSeriesDetail: (Int) -> Unit
) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500${series.poster_path}",
        contentDescription = series.name,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navigateToSeriesDetail(series.id)
            },
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun MovieSection(
    uiState: UiState,
    pagerState: PagerState,
    upcomingMovies: List<Movie>,
    navigateToDetail: (Int) -> Unit
) {
    Column {
        Text(
            text = "Upcoming",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) { page ->
                    MovieImageItem(upcomingMovies[page], navigateToDetail)
                }
                Spacer(modifier = Modifier.height(8.dp))

                DotIndicator(
                    totalDots = upcomingMovies.size,
                    selectedIndex = pagerState.currentPage
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Recently Added Movies",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(uiState.nowPlayingMovieList.shuffled()) { movie ->
                MovieItem(movie, navigateToDetail)
            }
        }
        Text(
            text = "Popular Movies",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(uiState.popularMovieList.shuffled()) { movie ->
                MovieItem(movie, navigateToDetail)
            }
        }
        Text(
            text = "Top Rated Movies",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(uiState.topRatedMovieList.shuffled()) { movie ->
                MovieItem(movie, navigateToDetail)
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    navigateToDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                vertical = 8.dp,
                horizontal = 4.dp
            )
            .clickable {
                navigateToDetail(movie.id)
            }
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
            contentDescription = movie.title,
            modifier = Modifier
                .size(170.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(120.dp),
            maxLines = 1
        )
        Row {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                tint = colorResource(R.color.main_orange),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = String.format(Locale.US, "%.1f", movie.vote_average),
                color = Color.White
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "(${movie.vote_count})",
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun TopBarHome(
    uiState: UiState
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AvatarProfileWithPlaceholder(imageUrl = null)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = "Hello, ${uiState.username}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Text(
                text = "Book and stream your favorite film",
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(R.drawable.ic_notifications),
            tint = colorResource(R.color.main_orange),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(
    @PreviewParameter(HomeScreenPreviewProvider::class) uiState: UiState,
) {
    HomeScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        navigateToMovieDetail = {},
        navigateToSeriesDetail = {}
    )
}