package com.salihakbas.movieappcompose.ui.detail

import com.salihakbas.movieappcompose.data.model.Genre
import com.salihakbas.movieappcompose.data.model.MovieCreditsResponse
import com.salihakbas.movieappcompose.data.model.MovieDetailResponse
import com.salihakbas.movieappcompose.data.model.SeriesCreditsResponse
import com.salihakbas.movieappcompose.data.model.TvShowResponse

object DetailContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val movie: MovieDetailResponse? = null,
        val movieCredit: MovieCreditsResponse? = null,
        val series: TvShowResponse? = null,
        val genre: Genre? = null,
        val seriesCredit: SeriesCreditsResponse? = null
    )

    sealed class UiAction


    sealed class UiEffect {
        data object NavigateBack : UiEffect()
        data class NavigateToTrailer(val movieId: Int) : UiEffect()
    }
}