package com.salihakbas.movieappcompose.domain.usecase.person

import com.salihakbas.movieappcompose.data.model.person.PersonDetailResponse
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class GetPersonDetailUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(personId: Int): PersonDetailResponse {
        return repository.getPersonDetail(personId)
    }
}