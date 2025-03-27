package com.salihakbas.movieappcompose.domain.repository

import com.salihakbas.movieappcompose.data.model.FavoriteEntity
import com.salihakbas.movieappcompose.data.source.local.FavoriteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val dao: FavoriteDao
) {
    fun getAllFavorites(): Flow<List<FavoriteEntity>> = dao.getAllFavorites()

    suspend fun addFavorite(movie: FavoriteEntity) = dao.insertFavorite(movie)

    suspend fun removeFavorite(movieId: Int) = dao.deleteFavorite(movieId)

    fun isFavorite(id: Int): Flow<Boolean> = dao.isFavorite(id)
}