package com.salihakbas.movieappcompose.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.salihakbas.movieappcompose.common.RoomTypeConverters
import com.salihakbas.movieappcompose.data.model.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainDao(): FavoriteDao
}