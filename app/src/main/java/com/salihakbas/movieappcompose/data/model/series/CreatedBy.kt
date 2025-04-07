package com.salihakbas.movieappcompose.data.model.series

import com.google.gson.annotations.SerializedName

data class CreatedBy(
    val id: Int = 0,
    @SerializedName("credit_id")
    val creditId: String?,
    val name: String?,
    val gender: Int = 0,
    @SerializedName("profile_path")
    val profilePath: String?
)
