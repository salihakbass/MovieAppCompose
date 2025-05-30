package com.salihakbas.movieappcompose.domain.repository

import com.salihakbas.movieappcompose.data.model.movie.Movie
import com.salihakbas.movieappcompose.data.model.movie.MovieCreditsResponse
import com.salihakbas.movieappcompose.data.model.movie.MovieDetailResponse
import com.salihakbas.movieappcompose.data.model.movie.MovieTrailerResponse
import com.salihakbas.movieappcompose.data.model.person.PersonDetailResponse
import com.salihakbas.movieappcompose.data.model.series.Series
import com.salihakbas.movieappcompose.data.model.series.SeriesCreditsResponse
import com.salihakbas.movieappcompose.data.model.series.TvShowResponse

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

    suspend fun getPersonDetail(
        personId: Int,
        language: String = "en-US",
        appendToResponse: String? = null
    ): PersonDetailResponse

    suspend fun getSimilarMovies(
        movieId: Int,
        language: String = "en-US",
        page: Int = 1
    ): List<Movie>

    suspend fun getSimilarSeries(
        seriesId: Int,
        language: String = "en-US",
        page: Int = 1
    ): List<Series>

    suspend fun getSearchMovies(
        query: String,
        language: String = "en-US",
        page: Int = 1,
        includeAdult: Boolean = false
    ): List<Movie>
}