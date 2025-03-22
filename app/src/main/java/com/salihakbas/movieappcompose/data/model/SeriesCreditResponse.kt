package com.salihakbas.movieappcompose.data.model

data class SeriesCreditsResponse(
    val cast: List<SeriesCast> = emptyList(),
    val crew: List<SeriesCrew> = emptyList()
)

data class SeriesCast(
    val adult: Boolean = true,
    val gender: Int = 0,
    val id: Int = 0,
    val known_for_department: String? = null,
    val name: String? = null,
    val original_name: String? = null,
    val popularity: Double = 0.0,
    val profile_path: String? = null,
    val character: String? = null,
    val credit_id: String? = null,
    val order: Int = 0
)

data class SeriesCrew(
    val adult: Boolean = true,
    val gender: Int = 0,
    val id: Int = 0,
    val known_for_department: String? = null,
    val name: String? = null,
    val original_name: String? = null,
    val popularity: Double = 0.0,
    val profile_path: String? = null,
    val credit_id: String? = null,
    val department: String? = null,
    val job: String? = null
)