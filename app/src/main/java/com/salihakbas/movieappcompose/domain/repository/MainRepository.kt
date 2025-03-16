package com.salihakbas.movieappcompose.domain.repository

import com.salihakbas.movieappcompose.data.model.Movie
import com.salihakbas.movieappcompose.data.model.Series

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

}