package com.salihakbas.movieappcompose.data.model

data class TvShowResponse(
    val adult: Boolean = true,
    val backdrop_path: String?,
    val created_by: List<CreatedBy> = emptyList(),
    val episode_run_time: List<Int> = emptyList(),
    val first_air_date: String?,
    val genres: List<Genre> = emptyList(),
    val homepage: String?,
    val id: Int = 0,
    val in_production: Boolean = true,
    val languages: List<String> = emptyList(),
    val last_air_date: String?,
    val last_episode_to_air: Episode?,
    val name: String?,
    val next_episode_to_air: Episode?,
    val networks: List<Network> = emptyList(),
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val origin_country: List<String> = emptyList(),
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double = 0.0,
    val poster_path: String?,
    val production_companies: List<ProductionCompany> = emptyList(),
    val production_countries: List<ProductionCountries> = emptyList(),
    val seasons: List<Season> = emptyList(),
    val spoken_languages: List<SpokenLanguage> = emptyList(),
    val status: String?,
    val tagline: String?,
    val type: String?,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)

data class CreatedBy(
    val id: Int = 0,
    val credit_id: String?,
    val name: String?,
    val gender: Int = 0,
    val profile_path: String?
)

data class Episode(
    val id: Int = 0,
    val name: String?,
    val overview: String?,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val air_date: String?,
    val episode_number: Int = 0,
    val production_code: String?,
    val runtime: Int = 0,
    val season_number: Int = 0,
    val show_id: Int = 0,
    val still_path: String?
)

data class Network(
    val id: Int = 0,
    val logo_path: String?,
    val name: String?,
    val origin_country: String?
)

data class Season(
    val air_date: String?,
    val episode_count: Int = 0,
    val id: Int = 0,
    val name: String?,
    val overview: String?,
    val poster_path: String?,
    val season_number: Int = 0,
    val vote_average: Int = 0
)