package com.salihakbas.movieappcompose.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salihakbas.movieappcompose.domain.usecase.GetMovieDetailUseCase
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
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    init {
        val movieId = savedStateHandle.get<Int>("movieId") ?: 0
        getMovieDetail(movieId)
    }

    fun onAction(uiAction: UiAction) {

    }

    private fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        try {
            val movieDetail = getMovieDetailUseCase(movieId)
            updateUiState { copy(isLoading = false, movie = movieDetail) }
        } catch (e: Exception) {
            updateUiState { copy(isLoading = false) }
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}