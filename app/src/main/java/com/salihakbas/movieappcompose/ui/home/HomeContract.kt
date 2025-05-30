package com.salihakbas.movieappcompose.ui.home

import com.salihakbas.movieappcompose.data.model.movie.Movie
import com.salihakbas.movieappcompose.data.model.series.Series

object HomeContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val popularMovieList: List<Movie> = emptyList(),
        val nowPlayingMovieList: List<Movie> = emptyList(),
        val topRatedMovieList: List<Movie> = emptyList(),
        val upcomingMovieList: List<Movie> = emptyList(),
        val airingTodayTvSeriesList: List<Series> = emptyList(),
        val onTheAirSeriesList: List<Series> = emptyList(),
        val popularSeriesList: List<Series> = emptyList(),
        val topRatedSeriesList: List<Series> = emptyList(),
        val username: String = ""
    )

    sealed class UiAction

    sealed class UiEffect {
        data class NavigateToMovieDetail(val movieId: Int) : UiEffect()
        data class NavigateToSeriesDetail(val seriesId: Int) : UiEffect()
    }
}