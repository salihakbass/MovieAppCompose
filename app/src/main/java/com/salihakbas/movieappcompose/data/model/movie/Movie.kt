package com.salihakbas.movieappcompose.data.model.movie

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("video")
    val video: Boolean
)
