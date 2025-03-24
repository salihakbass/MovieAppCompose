package com.salihakbas.movieappcompose.domain.repository

import com.salihakbas.movieappcompose.data.model.Movie
import com.salihakbas.movieappcompose.data.model.MovieCreditsResponse
import com.salihakbas.movieappcompose.data.model.MovieDetailResponse
import com.salihakbas.movieappcompose.data.model.MovieTrailerResponse
import com.salihakbas.movieappcompose.data.model.Series
import com.salihakbas.movieappcompose.data.model.SeriesCreditsResponse
import com.salihakbas.movieappcompose.data.model.TvShowResponse

interface MainRepository {
    suspend fun getPopularMovies(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null
    ): List<Movie>

    suspend fun getNowPlayingMovies(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null
    ): List<Movie>

    suspend fun getTopRatedMovies(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null
    ): List<Movie>

    suspend fun getUpcomingMovies(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null
    ): List<Movie>

    suspend fun getAiringTodayTvSeries(
        language: String = "en-US",
        page: Int = 1,
        timezone: String? = null
    ): List<Series>

    suspend fun getOnTheAirSeries(
        language: String = "en-US",
        page: Int = 1,
        timezone: String? = null
    ): List<Series>

    suspend fun getPopularSeries(
        language: String = "en-US",
        page: Int = 1,
        timezone: String? = null
    ): List<Series>

    suspend fun getTopRatedSeries(
        language: String = "en-US",
        page: Int = 1,
        timezone: String? = null
    ): List<Series>

    suspend fun getMovieDetail(
        movieId: Int,
        language: String = "en-US",
        appendToResponse: String? = null
    ): MovieDetailResponse

    suspend fun getMovieCredits(
        movieId: Int,
        language: String = "en-US"
    ): MovieCreditsResponse

    suspend fun getSeriesDetail(
        seriesId: Int,
        language: String = "en-US",
        appendToResponse: String? = null
    ): TvShowResponse

    suspend fun getSeriesCredits(
        seriesId: Int,
        language: String = "en-US"
    ): SeriesCreditsResponse

    suspend fun getMovieTrailer(
        movieId: Int,
        language: String = "en-US"
    ): MovieTrailerResponse

}