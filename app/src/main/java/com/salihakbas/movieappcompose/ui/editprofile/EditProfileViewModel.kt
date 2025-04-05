package com.salihakbas.movieappcompose.ui.editprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import com.salihakbas.movieappcompose.common.Resource
import com.salihakbas.movieappcompose.domain.repository.FirebaseAuthRepository
import com.salihakbas.movieappcompose.ui.editprofile.EditProfileContract.UiAction
import com.salihakbas.movieappcompose.ui.editprofile.EditProfileContract.UiEffect
import com.salihakbas.movieappcompose.ui.editprofile.EditProfileContract.UiState
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
class EditProfileViewModel @Inject constructor() : ViewModel() {

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
                    val email = snapshot.child("email").getValue(String::class.java) ?: ""
                    val phoneNumber = snapshot.child("phone").getValue(String::class.java) ?: ""

                    updateUiState {
                        copy(
                            isLoading = false,
                            name = name,
                            email = email,
                            phoneNumber = phoneNumber,
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