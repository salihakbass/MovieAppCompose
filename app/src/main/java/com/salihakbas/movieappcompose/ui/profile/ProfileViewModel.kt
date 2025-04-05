package com.salihakbas.movieappcompose.ui.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.salihakbas.movieappcompose.ui.profile.ProfileContract.UiAction
import com.salihakbas.movieappcompose.ui.profile.ProfileContract.UiEffect
import com.salihakbas.movieappcompose.ui.profile.ProfileContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: UiAction) {
    }

    fun fetchUserFromRealtimeDatabase(userId: String) {
        updateUiState { copy(isLoading = true) }

        val database = FirebaseDatabase.getInstance().reference
        database.child("users").child(userId).get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val name = snapshot.child("name").getValue(String::class.java) ?: ""
                    val surname = snapshot.child("surname").getValue(String::class.java) ?: ""

                    updateUiState {
                        copy(
                            isLoading = false,
                            name = name,
                            surname = surname
                        )
                    }
                } else {
                    updateUiState { copy(isLoading = false) }
                }
            }
            .addOnFailureListener { e ->
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