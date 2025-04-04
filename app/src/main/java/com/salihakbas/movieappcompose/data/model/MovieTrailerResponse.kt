package com.salihakbas.movieappcompose.data.model

data class MovieTrailerResponse(
    val id: Int,
    val results: List<MovieTrailer>
)

data class MovieTrailer(
    val id: String,
    val iso_639_1: String,
    val iso_3166_1: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean = true,
    val published_at: String
)
