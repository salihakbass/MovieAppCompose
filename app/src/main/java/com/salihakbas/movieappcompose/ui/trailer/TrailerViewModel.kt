package com.salihakbas.movieappcompose.ui.trailer

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salihakbas.movieappcompose.domain.usecase.movie.GetMovieDetailUseCase
import com.salihakbas.movieappcompose.domain.usecase.movie.GetMovieTrailerUseCase
import com.salihakbas.movieappcompose.ui.trailer.TrailerContract.UiAction
import com.salihakbas.movieappcompose.ui.trailer.TrailerContract.UiEffect
import com.salihakbas.movieappcompose.ui.trailer.TrailerContract.UiState
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
class TrailerViewModel @Inject constructor(
    private val getMovieTrailerUseCase: GetMovieTrailerUseCase,
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    init {
        val movieId = savedStateHandle.get<Int>("movieId")
        movieId?.let {
            getMovieTrailer(it)
            getMovieDetail(it)
        }
    }


    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadTrailer -> getMovieTrailer(uiAction.movieId)
        }
    }

    private fun getMovieTrailer(movieId: Int) = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }

        try {
            val response = getMovieTrailerUseCase(movieId)
            val trailerKey = response.results.firstOrNull {
                it.site == "YouTube" && it.type == "Trailer"
            }?.key

            Log.d("TrailerViewModel", "Video key: $trailerKey")

            updateUiState {
                copy(isLoading = false, videoKey = trailerKey)
            }
        } catch (e: Exception) {
            updateUiState { copy(isLoading = false) }
            emitUiEffect(UiEffect.Error(e))
        }
    }

    private fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }

        try {
            val response = getMovieDetailUseCase(movieId)
            updateUiState {
                copy(isLoading = false, movie = response)
            }
        } catch (e: Exception) {
            updateUiState { copy(isLoading = false) }
            emitUiEffect(UiEffect.Error(e))
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}