package com.salihakbas.movieappcompose.ui.person

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salihakbas.movieappcompose.domain.usecase.person.GetPersonDetailUseCase
import com.salihakbas.movieappcompose.ui.person.PersonContract.UiAction
import com.salihakbas.movieappcompose.ui.person.PersonContract.UiEffect
import com.salihakbas.movieappcompose.ui.person.PersonContract.UiState
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
class PersonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPersonDetailUseCase: GetPersonDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    init {
        val personId = savedStateHandle.get<Int>("personId") ?: 0
        getPersonDetail(personId)
    }

    fun onAction(uiAction: UiAction) {
    }

    private fun getPersonDetail(personId: Int) = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        try {
            val personDetail = getPersonDetailUseCase(personId)
            updateUiState { copy(isLoading = false, personDetail = personDetail) }
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