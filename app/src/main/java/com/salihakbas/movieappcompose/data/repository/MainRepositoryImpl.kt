package com.salihakbas.movieappcompose.data.repository

import com.salihakbas.movieappcompose.data.model.movie.Movie
import com.salihakbas.movieappcompose.data.model.movie.MovieCreditsResponse
import com.salihakbas.movieappcompose.data.model.movie.MovieDetailResponse
import com.salihakbas.movieappcompose.data.model.movie.MovieTrailerResponse
import com.salihakbas.movieappcompose.data.model.person.PersonDetailResponse
import com.salihakbas.movieappcompose.data.model.series.Series
import com.salihakbas.movieappcompose.data.model.series.SeriesCreditsResponse
import com.salihakbas.movieappcompose.data.model.series.TvShowResponse
import com.salihakbas.movieappcompose.data.source.remote.MainService
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
) : MainRepository {
    override suspend fun getPopularMovies(
        language: String,
        page: Int,
        region: String?
    ): List<Movie> {
        return mainService.getPopularMovies(language, page, region).results
    }

    override suspend fun getNowPlayingMovies(
        language: String,
        page: Int,
        region: String?
    ): List<Movie> {
        return mainService.getNowPlayingMovies(language, page, region).results
    }

    override suspend fun getTopRatedMovies(
        language: String,
        page: Int,
        region: String?
    ): List<Movie> {
        return mainService.getTopRatedMovies(language, page, region).results
    }

    override suspend fun getUpcomingMovies(
        language: String,
        page: Int,
        region: String?
    ): List<Movie> {
        return mainService.getUpcomingMovies(language, page, region).results
    }

    override suspend fun getAiringTodayTvSeries(
        language: String,
        page: Int,
        timezone: String?
    ): List<Series> {
        return mainService.getAiringTodayTvSeries(language, page, timezone).results
    }

    override suspend fun getOnTheAirSeries(
        language: String,
        page: Int,
        timezone: String?
    ): List<Series> {
        return mainService.getOnTheAirSeries(language, page, timezone).results
    }

    override suspend fun getPopularSeries(
        language: String,
        page: Int,
        timezone: String?
    ): List<Series> {
        return mainService.getPopularSeries(language, page, timezone).results
    }

    override suspend fun getTopRatedSeries(
        language: String,
        page: Int,
        timezone: String?
    ): List<Series> {
        return mainService.getTopRatedSeries(language, page, timezone).results
    }

    override suspend fun getMovieDetail(
        movieId: Int,
        language: String,
        appendToResponse: String?
    ): MovieDetailResponse {
        return mainService.getMovieDetail(movieId, language, appendToResponse)
    }

    override suspend fun getMovieCredits(movieId: Int, language: String): MovieCreditsResponse {
        return mainService.getMovieCredits(movieId, language)
    }

    override suspend fun getSeriesDetail(
        seriesId: Int,
        language: String,
        appendToResponse: String?
    ): TvShowResponse {
        return mainService.getSerieDetail(seriesId, language, appendToResponse)
    }

    override suspend fun getSeriesCredits(seriesId: Int, language: String): SeriesCreditsResponse {
        return mainService.getSerieCredits(seriesId, language)
    }

    override suspend fun getMovieTrailer(movieId: Int, language: String): MovieTrailerResponse {
        return mainService.getMovieTrailer(movieId, language)
    }

    override suspend fun getPersonDetail(
        personId: Int,
        language: String,
        appendToResponse: String?
    ): PersonDetailResponse {
        return mainService.getPersonDetail(personId, appendToResponse, language)
    }

    override suspend fun getSimilarMovies(movieId: Int, language: String, page: Int): List<Movie> {
        return mainService.getSimilarMovies(movieId, language, page).results
    }

    override suspend fun getSimilarSeries(
        seriesId: Int,
        language: String,
        page: Int
    ): List<Series> {
        return mainService.getSimilarSeries(seriesId, language, page).results
    }

    override suspend fun getSearchMovies(
        query: String,
        language: String,
        page: Int,
        includeAdult: Boolean
    ): List<Movie> {
        return mainService.getSearchMovies(query, language, page, includeAdult).results
    }
}