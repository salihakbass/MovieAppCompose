package com.salihakbas.movieappcompose.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salihakbas.movieappcompose.data.model.FavoriteEntity
import com.salihakbas.movieappcompose.data.model.toFavoriteEntity
import com.salihakbas.movieappcompose.domain.repository.FavoriteRepository
import com.salihakbas.movieappcompose.domain.usecase.movie.GetMovieCreditsUseCase
import com.salihakbas.movieappcompose.domain.usecase.movie.GetMovieDetailUseCase
import com.salihakbas.movieappcompose.domain.usecase.movie.GetSimilarMoviesUseCase
import com.salihakbas.movieappcompose.domain.usecase.serie.GetSeriesCreditsUseCase
import com.salihakbas.movieappcompose.domain.usecase.serie.GetSeriesDetailUseCase
import com.salihakbas.movieappcompose.domain.usecase.serie.GetSimilarSeriesUseCase
import com.salihakbas.movieappcompose.ui.detail.DetailContract.UiAction
import com.salihakbas.movieappcompose.ui.detail.DetailContract.UiEffect
import com.salihakbas.movieappcompose.ui.detail.DetailContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    private val getSeriesDetailUseCase: GetSeriesDetailUseCase,
    private val getSeriesCreditsUseCase: GetSeriesCreditsUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val getSimilarSeriesUseCase: GetSimilarSeriesUseCase,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    init {
        val type = savedStateHandle.get<String>("type")
        val id = savedStateHandle.get<Int>("id") ?: 0

        when (type) {
            "movie" -> {
                getMovieDetail(id)
                getMovieCredits(id)
                getSimilarMovies(id)
            }

            "series" -> {
                getSeriesDetail(id)
                getSeriesCredits(id)
                getSimilarSeries(id)
            }
        }
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.ToggleFavorite -> {
                val movie = uiState.value.movie ?: return
                if (uiState.value.isFavorite) {
                    removeFromFavorites(movie.id)
                } else {
                    addToFavorites(movie.toFavoriteEntity())
                }
            }
        }
    }

    private fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        try {
            val movieDetail = getMovieDetailUseCase(movieId)
            updateUiState { copy(isLoading = false, movie = movieDetail) }
            checkIsFavorite(movieId)
        } catch (e: Exception) {
            updateUiState { copy(isLoading = false) }
        }
    }

    private fun getMovieCredits(movieId: Int) = viewModelScope.launch {
        try {
            val movieCredits = getMovieCreditsUseCase(movieId)
            updateUiState { copy(isLoading = false, movieCredit = movieCredits) }
        } catch (e: Exception) {
            updateUiState { copy(isLoading = false) }
        }
    }

    private fun getSeriesCredits(seriesId: Int) = viewModelScope.launch {
        try {
            val seriesCredits = getSeriesCreditsUseCase(seriesId)
            updateUiState { copy(isLoading = false, seriesCredit = seriesCredits) }
        } catch (e: Exception) {
            updateUiState { copy(isLoading = false) }
        }
    }

    private fun getSeriesDetail(seriesId: Int) = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        try {
            val seriesDetail = getSeriesDetailUseCase(seriesId)
            updateUiState { copy(isLoading = false, series = seriesDetail) }
        } catch (e: Exception) {
            updateUiState { copy(isLoading = false) }
        }
    }

    private fun getSimilarMovies(movieId: Int) = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        val result = getSimilarMoviesUseCase(movieId)
        result.onSuccess {
            updateUiState { copy(isLoading = false, similarMovies = it) }
        }.onFailure {
            updateUiState { copy(isLoading = false) }
        }
    }

    private fun getSimilarSeries(seriesId: Int) = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        val result = getSimilarSeriesUseCase(seriesId)
        result.onSuccess {
            updateUiState { copy(isLoading = false, similarSeries = it) }
        }.onFailure {
            updateUiState { copy(isLoading = false) }
        }
    }

    private fun addToFavorites(movie: FavoriteEntity) = viewModelScope.launch {
        favoriteRepository.addFavorite(movie)
    }

    private fun removeFromFavorites(movieId: Int) = viewModelScope.launch {
        favoriteRepository.removeFavorite(movieId)
    }

    private fun checkIsFavorite(id: Int) {
        viewModelScope.launch {
            favoriteRepository.isFavorite(id).collect { isFav ->
                updateUiState { copy(isFavorite = isFav) }
            }
        }
    }


    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}