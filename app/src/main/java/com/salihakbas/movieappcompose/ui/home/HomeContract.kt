package com.salihakbas.movieappcompose.ui.home

import com.salihakbas.movieappcompose.data.model.Movie

object HomeContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val popularMovieList: List<Movie> = emptyList(),
        val nowPlayingMovieList: List<Movie> = emptyList(),
        val topRatedMovieList: List<Movie> = emptyList()
    )

    sealed class UiAction

    sealed class UiEffect
}