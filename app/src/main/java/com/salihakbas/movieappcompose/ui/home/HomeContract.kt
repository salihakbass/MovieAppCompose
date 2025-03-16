package com.salihakbas.movieappcompose.ui.home

import com.salihakbas.movieappcompose.data.model.Movie
import com.salihakbas.movieappcompose.data.model.Series

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
    )

    sealed class UiAction

    sealed class UiEffect
}