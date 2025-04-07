package com.salihakbas.movieappcompose.data.model.movie

data class MovieCreditsResponse(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)