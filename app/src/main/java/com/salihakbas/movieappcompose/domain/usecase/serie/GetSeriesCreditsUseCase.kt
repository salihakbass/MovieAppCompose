package com.salihakbas.movieappcompose.domain.usecase.serie

import com.salihakbas.movieappcompose.data.model.SeriesCreditsResponse
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class GetSeriesCreditsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(serieId: Int): SeriesCreditsResponse {
        return try {
            repository.getSeriesCredits(serieId)
        } catch (e: Exception) {
            throw e
        }
    }
}