package com.salihakbas.movieappcompose.domain.usecase

import com.salihakbas.movieappcompose.data.model.Movie
import com.salihakbas.movieappcompose.data.source.remote.MainService
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val mainService: MainService
) {
    suspend operator fun invoke(language: String = "en-US", page: Int = 1): Result<List<Movie>> {
        return try {
            val movieResponse = mainService.getNowPlayingMovies(language, page)
            Result.success(movieResponse.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}