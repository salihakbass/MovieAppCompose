package com.salihakbas.movieappcompose.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class MainEntity(
    @PrimaryKey
    val id: Int,
    val title: String
)