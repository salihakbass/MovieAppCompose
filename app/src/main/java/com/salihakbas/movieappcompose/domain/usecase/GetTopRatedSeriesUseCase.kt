package com.salihakbas.movieappcompose.domain.usecase

import com.salihakbas.movieappcompose.data.model.Series
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class GetTopRatedSeriesUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(language: String = "en-US", page: Int = 1): Result<List<Series>> {
        return try {
            val response = repository.getTopRatedSeries(language, page)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}