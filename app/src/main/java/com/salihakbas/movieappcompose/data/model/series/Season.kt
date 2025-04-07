package com.salihakbas.movieappcompose.data.model.series

import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("episode_count")
    val episodeCount: Int = 0,
    val id: Int = 0,
    val name: String?,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("season_number")
    val seasonNumber: Int = 0,
    @SerializedName("vote_average")
    val voteAverage: Int = 0
)
