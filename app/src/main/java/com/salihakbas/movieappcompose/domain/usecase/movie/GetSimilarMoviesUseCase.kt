package com.salihakbas.movieappcompose.domain.usecase.movie

import com.salihakbas.movieappcompose.data.model.Movie
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(movieId: Int): Result<List<Movie>> {
        return try {
            val similarMovieResponse = repository.getSimilarMovies(movieId)
            Result.success(similarMovieResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}