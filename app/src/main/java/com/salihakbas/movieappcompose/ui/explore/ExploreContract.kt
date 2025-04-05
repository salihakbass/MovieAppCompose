package com.salihakbas.movieappcompose.ui.explore

import com.salihakbas.movieappcompose.data.model.Movie

object ExploreContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val query: String = "",
        val movies: List<Movie> = emptyList(),
        val suggestedMovies: List<Movie> = emptyList(),
        val hasSearched: Boolean = false
    )

    sealed class UiAction {
        data class OnQueryChanged(val query: String) : UiAction()
        data object OnSearch : UiAction()
    }

    sealed class UiEffect
}