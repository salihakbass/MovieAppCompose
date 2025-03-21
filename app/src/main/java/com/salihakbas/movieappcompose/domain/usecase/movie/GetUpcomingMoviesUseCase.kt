package com.salihakbas.movieappcompose.domain.usecase.movie

import com.salihakbas.movieappcompose.data.model.Movie
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(language: String = "en-US", page: Int = 1): Result<List<Movie>> {
        return try {
            val movieResponse = repository.getUpcomingMovies(language, page)
            Result.success(movieResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}