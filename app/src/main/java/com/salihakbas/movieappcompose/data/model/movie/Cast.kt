package com.salihakbas.movieappcompose.data.model.movie

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("character")
    val character: String,
    @SerializedName("profile_path")
    val profilePath: String?,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("cast_id")
    val castId: Int,
    @SerializedName("credit_id")
    val creditId: String,
    @SerializedName("order")
    val order: Int
)
