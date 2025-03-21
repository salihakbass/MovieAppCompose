package com.salihakbas.movieappcompose.domain.usecase

import com.salihakbas.movieappcompose.data.model.MovieDetailResponse
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(movieId: Int): MovieDetailResponse {
        return try {
            repository.getMovieDetail(movieId)
        } catch (e: Exception) {
            throw e
        }
    }
}