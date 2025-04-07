package com.salihakbas.movieappcompose.ui.detail

import com.salihakbas.movieappcompose.data.model.common.Genre
import com.salihakbas.movieappcompose.data.model.movie.Movie
import com.salihakbas.movieappcompose.data.model.movie.MovieCreditsResponse
import com.salihakbas.movieappcompose.data.model.movie.MovieDetailResponse
import com.salihakbas.movieappcompose.data.model.series.Series
import com.salihakbas.movieappcompose.data.model.series.SeriesCreditsResponse
import com.salihakbas.movieappcompose.data.model.series.TvShowResponse

object DetailContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val movie: MovieDetailResponse? = null,
        val movieCredit: MovieCreditsResponse? = null,
        val series: TvShowResponse? = null,
        val genre: Genre? = null,
        val seriesCredit: SeriesCreditsResponse? = null,
        val similarMovies: List<Movie> = emptyList(),
        val similarSeries: List<Series> = emptyList()
    )

    sealed class UiAction

    sealed class UiEffect {
        data object NavigateBack : UiEffect()
        data class NavigateToTrailer(val movieId: Int) : UiEffect()
    }
}