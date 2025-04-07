package com.salihakbas.movieappcompose.ui.person

import com.salihakbas.movieappcompose.data.model.person.PersonDetailResponse

object PersonContract {
    data class UiState(
        val isLoading: Boolean = false,
        val personDetail: PersonDetailResponse? = null,
        val error: String = ""
    )

    sealed class UiAction

    sealed class UiEffect
}
