package com.salihakbas.movieappcompose.data.model.series

import com.google.gson.annotations.SerializedName

data class Episode(
    val id: Int = 0,
    val name: String?,
    val overview: String?,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("episode_number")
    val episodeNumber: Int = 0,
    @SerializedName("production_code")
    val productionCode: String?,
    val runtime: Int = 0,
    @SerializedName("season_number")
    val seasonNumber: Int = 0,
    @SerializedName("show_id")
    val showId: Int = 0,
    @SerializedName("still_path")
    val stillPath: String?
)
