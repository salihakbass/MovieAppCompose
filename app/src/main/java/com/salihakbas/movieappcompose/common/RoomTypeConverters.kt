package com.salihakbas.movieappcompose.common

import androidx.room.TypeConverter

class RoomTypeConverters {

    @TypeConverter
    fun fromIntList(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toIntList(data: String): List<Int> {
        return if (data.isBlank()) emptyList()
        else data.split(",").map { it.toInt() }
    }
}