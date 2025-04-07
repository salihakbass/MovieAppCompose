package com.salihakbas.movieappcompose.data.model.series

import com.google.gson.annotations.SerializedName
import com.salihakbas.movieappcompose.data.model.common.Genre
import com.salihakbas.movieappcompose.data.model.common.ProductionCompany
import com.salihakbas.movieappcompose.data.model.common.ProductionCountries
import com.salihakbas.movieappcompose.data.model.common.SpokenLanguage

data class TvShowResponse(
    val adult: Boolean = true,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("created_by")
    val createdBy: List<CreatedBy> = emptyList(),
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int> = emptyList(),
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    val genres: List<Genre> = emptyList(),
    val homepage: String?,
    val id: Int = 0,
    @SerializedName("in_production")
    val inProduction: Boolean = true,
    val languages: List<String> = emptyList(),
    @SerializedName("last_air_date")
    val lastAirDate: String?,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: Episode?,
    val name: String?,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Episode?,
    val networks: List<Network> = emptyList(),
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int = 0,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int = 0,
    @SerializedName("origin_country")
    val originCountry: List<String> = emptyList(),
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_name")
    val originalName: String?,
    val overview: String?,
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany> = emptyList(),
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountries> = emptyList(),
    val seasons: List<Season> = emptyList(),
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val status: String?,
    val tagline: String?,
    val type: String?,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0
)
