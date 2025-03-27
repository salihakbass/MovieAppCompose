package com.salihakbas.movieappcompose.ui.favorite

import com.salihakbas.movieappcompose.data.model.FavoriteEntity

object FavoriteContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val favoriteMovie: List<FavoriteEntity> = emptyList()
    )

    sealed class UiAction

    sealed class UiEffect
}