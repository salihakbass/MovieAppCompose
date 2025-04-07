package com.salihakbas.movieappcompose.ui.trailer

import com.salihakbas.movieappcompose.data.model.movie.MovieDetailResponse

object TrailerContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val videoKey: String? = null,
        val movie: MovieDetailResponse? = null
    )

    sealed class UiAction {
        data class LoadTrailer(val movieId: Int) : UiAction()
    }

    sealed class UiEffect {
        data class Error(val throwable: Throwable) : UiEffect()
    }
}