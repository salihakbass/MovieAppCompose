package com.salihakbas.movieappcompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class FavoriteEntity(
    @PrimaryKey
    val id: Int,
    val title: String
)