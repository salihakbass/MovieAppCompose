package com.salihakbas.movieappcompose.domain.usecase.movie

import com.salihakbas.movieappcompose.data.model.MovieCreditsResponse
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(movieId: Int): MovieCreditsResponse {
        return try {
            repository.getMovieCredits(movieId)
        } catch (e: Exception) {
            throw e
        }
    }
}