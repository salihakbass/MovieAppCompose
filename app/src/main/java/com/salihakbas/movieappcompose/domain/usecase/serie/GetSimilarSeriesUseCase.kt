package com.salihakbas.movieappcompose.domain.usecase.serie

import com.salihakbas.movieappcompose.data.model.Series
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class GetSimilarSeriesUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(seriesId: Int): Result<List<Series>> {
        return try {
            val series = repository.getSimilarSeries(seriesId)
            Result.success(series)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}