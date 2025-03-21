package com.salihakbas.movieappcompose.ui.detail

import com.salihakbas.movieappcompose.data.model.MovieDetailResponse

object DetailContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val movie: MovieDetailResponse? = null
    )

    sealed class UiAction


    sealed class UiEffect {
        data object NavigateBack : UiEffect()
    }
}