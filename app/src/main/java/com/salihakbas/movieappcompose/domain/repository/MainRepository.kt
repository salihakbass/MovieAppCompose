package com.salihakbas.movieappcompose.domain.repository

import com.salihakbas.movieappcompose.data.model.Movie

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

}