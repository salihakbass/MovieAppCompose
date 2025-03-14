package com.salihakbas.movieappcompose.data.repository

import com.salihakbas.movieappcompose.data.model.Movie
import com.salihakbas.movieappcompose.data.source.local.MainDao
import com.salihakbas.movieappcompose.data.source.remote.MainService
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
    private val mainDao: MainDao,
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

}