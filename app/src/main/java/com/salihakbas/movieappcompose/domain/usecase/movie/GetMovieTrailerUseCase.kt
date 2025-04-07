package com.salihakbas.movieappcompose.domain.usecase.movie

import com.salihakbas.movieappcompose.data.model.movie.MovieTrailerResponse
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class GetMovieTrailerUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(movieId: Int): MovieTrailerResponse {
        return try {
            repository.getMovieTrailer(movieId)
        } catch (e: Exception) {
            throw e
        }
    }
}