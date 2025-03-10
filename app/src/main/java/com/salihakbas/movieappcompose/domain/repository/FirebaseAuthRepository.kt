package com.salihakbas.movieappcompose.domain.repository

import com.salihakbas.movieappcompose.common.Resource

interface FirebaseAuthRepository {
    suspend fun createUserWithEmailAndPassword(
        nameSurname: String,
        phoneNumber: String,
        email: String,
        password: String,
    ): Resource<String>

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<String>

    suspend fun isUserLoggedIn(): Boolean

    suspend fun sendPasswordResetEmail(
        email: String
    ): Resource<String>

    suspend fun signOut(): Resource<String>

    suspend fun changePassword(
        email: String,
        oldPassword: String,
        newPassword: String
    ): Resource<String>

    suspend fun getCurrentUserEmail(): String?
}