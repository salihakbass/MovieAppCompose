package com.salihakbas.movieappcompose.data.model.series

import com.google.gson.annotations.SerializedName

data class Network(
    val id: Int = 0,
    @SerializedName("logo_path")
    val logoPath: String?,
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?
)
