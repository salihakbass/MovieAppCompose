package com.salihakbas.movieappcompose.domain.usecase.serie

import com.salihakbas.movieappcompose.data.model.series.TvShowResponse
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class GetSeriesDetailUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(seriesId: Int): TvShowResponse {
        return try {
            repository.getSeriesDetail(seriesId)
        } catch (e: Exception) {
            throw e
        }
    }
}