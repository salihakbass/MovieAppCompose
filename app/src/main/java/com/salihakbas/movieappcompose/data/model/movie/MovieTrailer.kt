package com.salihakbas.movieappcompose.data.model.movie

import com.google.gson.annotations.SerializedName

data class MovieTrailer(
    val id: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean = true,
    @SerializedName("published_at")
    val publishedAt: String
)
