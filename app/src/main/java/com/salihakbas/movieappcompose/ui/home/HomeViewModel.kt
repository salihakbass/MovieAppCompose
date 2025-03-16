package com.salihakbas.movieappcompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salihakbas.movieappcompose.domain.usecase.GetAiringTodayTvSeriesUseCase
import com.salihakbas.movieappcompose.domain.usecase.GetNowPlayingMoviesUseCase
import com.salihakbas.movieappcompose.domain.usecase.GetOnTheAirSeriesUseCase
import com.salihakbas.movieappcompose.domain.usecase.GetPopularMoviesUseCase
import com.salihakbas.movieappcompose.domain.usecase.GetPopularSeriesUseCase
import com.salihakbas.movieappcompose.domain.usecase.GetTopRatedMoviesUseCase
import com.salihakbas.movieappcompose.domain.usecase.GetUpcomingMoviesUseCase
import com.salihakbas.movieappcompose.ui.home.HomeContract.UiAction
import com.salihakbas.movieappcompose.ui.home.HomeContract.UiEffect
import com.salihakbas.movieappcompose.ui.home.HomeContract.UiState
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
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getAiringTodayTvSeriesUseCase: GetAiringTodayTvSeriesUseCase,
    private val getOnTheAirSeriesUseCase: GetOnTheAirSeriesUseCase,
    private val getPopularSeriesUseCase: GetPopularSeriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    init {
        getPopularMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
        getUpcomingMovies()
        getAiringTodayTvSeries()
        getOnTheAirSeries()
        getPopularSeries()
    }

    fun onAction(uiAction: UiAction) {

    }

    private fun getPopularMovies() = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        val result = getPopularMoviesUseCase()
        updateUiState { copy(isLoading = false) }
        result.onSuccess { movies ->
            updateUiState { copy(popularMovieList = movies) }
        }.onFailure {

        }
    }

    private fun getNowPlayingMovies() = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        val result = getNowPlayingMoviesUseCase()
        updateUiState { copy(isLoading = false) }
        result.onSuccess { movies ->
            updateUiState { copy(nowPlayingMovieList = movies) }
        }.onFailure {

        }
    }

    private fun getTopRatedMovies() = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        val result = getTopRatedMoviesUseCase()
        updateUiState { copy(isLoading = false) }
        result.onSuccess { movies ->
            updateUiState { copy(topRatedMovieList = movies) }
        }.onFailure {

        }
    }

    private fun getUpcomingMovies() = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        val result = getUpcomingMoviesUseCase()
        updateUiState { copy(isLoading = false) }
        result.onSuccess { movies ->
            updateUiState { copy(upcomingMovieList = movies) }
        }.onFailure {

        }
    }

    private fun getAiringTodayTvSeries() = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        val result = getAiringTodayTvSeriesUseCase()
        updateUiState { copy(isLoading = false) }
        result.onSuccess { series ->
            updateUiState { copy(airingTodayTvSeriesList = series) }
        }.onFailure {

        }
    }

    private fun getOnTheAirSeries() = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        val result = getOnTheAirSeriesUseCase()
        updateUiState { copy(isLoading = false) }
        result.onSuccess { series ->
            updateUiState { copy(onTheAirSeriesList = series) }
        }.onFailure {

        }
    }

    private fun getPopularSeries() = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        val result = getPopularSeriesUseCase()
        updateUiState { copy(isLoading = false) }
        result.onSuccess { series ->
            updateUiState { copy(popularSeriesList = series) }
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