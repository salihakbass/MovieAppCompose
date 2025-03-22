package com.salihakbas.movieappcompose.data.model

data class MovieCreditsResponse(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)

data class Cast(
    val id: Int,
    val name: String,
    val original_name: String,
    val character: String,
    val profile_path: String?,
    val popularity: Double,
    val cast_id: Int,
    val credit_id: String,
    val order: Int
)

data class Crew(
    val id: Int,
    val name: String,
    val original_name: String,
    val job: String,
    val department: String,
    val profile_path: String?,
    val popularity: Double,
    val credit_id: String
)