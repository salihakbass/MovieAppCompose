package com.salihakbas.movieappcompose.domain.usecase.movie

import com.salihakbas.movieappcompose.data.model.movie.Movie
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(language: String = "en-US", page: Int = 1): Result<List<Movie>> {
        return try {
            val movieResponse = repository.getTopRatedMovies(language, page)
            Result.success(movieResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}