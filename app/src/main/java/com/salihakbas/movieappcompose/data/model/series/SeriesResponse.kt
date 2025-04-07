package com.salihakbas.movieappcompose.data.model.series

import com.google.gson.annotations.SerializedName

data class SeriesResponse(
    val page: Int,
    val results: List<Series>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
