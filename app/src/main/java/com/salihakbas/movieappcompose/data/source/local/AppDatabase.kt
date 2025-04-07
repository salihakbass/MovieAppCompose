package com.salihakbas.movieappcompose.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salihakbas.movieappcompose.data.model.entity.MainEntity

@Database(entities = [MainEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainDao(): FavoriteDao
}