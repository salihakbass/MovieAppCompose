package com.salihakbas.movieappcompose.data.model.movie

import com.google.gson.annotations.SerializedName

data class Crew(
    val id: Int,
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    val job: String,
    val department: String,
    @SerializedName("profile_path")
    val profilePath: String?,
    val popularity: Double,
    @SerializedName("credit_id")
    val creditId: String
)
