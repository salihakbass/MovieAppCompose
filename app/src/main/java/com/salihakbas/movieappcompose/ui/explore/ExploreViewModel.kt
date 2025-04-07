package com.salihakbas.movieappcompose.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salihakbas.movieappcompose.common.Resource
import com.salihakbas.movieappcompose.domain.usecase.movie.GetTopRatedMoviesUseCase
import com.salihakbas.movieappcompose.domain.usecase.movie.SearchMoviesUseCase
import com.salihakbas.movieappcompose.ui.explore.ExploreContract.UiAction
import com.salihakbas.movieappcompose.ui.explore.ExploreContract.UiEffect
import com.salihakbas.movieappcompose.ui.explore.ExploreContract.UiState
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
class ExploreViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    init {
        getSuggestedMovies()
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnQueryChanged -> {
                updateUiState { copy(query = uiAction.query, hasSearched = false) }

                if (uiAction.query.isEmpty()) {
                    updateUiState { copy(movies = emptyList()) }
                }
            }

            is UiAction.OnSearch -> {
                if (uiState.value.query.isNotBlank()) {
                    updateUiState { copy(hasSearched = true, isLoading = true) }
                    searchMovies(uiState.value.query)
                }
            }
        }
    }

    private fun searchMovies(query: String) = viewModelScope.launch {
        when (val result = searchMoviesUseCase(query)) {
            is Resource.Success -> {
                updateUiState { copy(isLoading = false, movies = result.data) }
            }

            is Resource.Error -> {
                updateUiState { copy(isLoading = false) }
            }
        }
    }

    private fun getSuggestedMovies() = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        val result = getTopRatedMoviesUseCase()
        updateUiState { copy(isLoading = false) }
        result.onSuccess { movies ->
            updateUiState { copy(suggestedMovies = movies) }
        }.onFailure {
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}