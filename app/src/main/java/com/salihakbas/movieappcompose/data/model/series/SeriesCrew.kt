package com.salihakbas.movieappcompose.data.model.series

import com.google.gson.annotations.SerializedName

data class SeriesCrew(
    val adult: Boolean = true,
    val gender: Int = 0,
    val id: Int = 0,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    val name: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    val popularity: Double = 0.0,
    @SerializedName("profile_path")
    val profilePath: String? = null,
    @SerializedName("credit_id")
    val creditId: String? = null,
    val department: String? = null,
    val job: String? = null
)
