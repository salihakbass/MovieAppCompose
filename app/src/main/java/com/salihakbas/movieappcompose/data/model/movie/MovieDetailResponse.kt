package com.salihakbas.movieappcompose.data.model.movie

import com.google.gson.annotations.SerializedName
import com.salihakbas.movieappcompose.data.model.common.Genre
import com.salihakbas.movieappcompose.data.model.common.ProductionCompany
import com.salihakbas.movieappcompose.data.model.common.ProductionCountries
import com.salihakbas.movieappcompose.data.model.common.SpokenLanguage

data class MovieDetailResponse(
    val adult: Boolean = true,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: String,
    val budget: Int = 0,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int = 0,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountries>,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int = 0,
    val runtime: Int = 0,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean = true,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0
)
