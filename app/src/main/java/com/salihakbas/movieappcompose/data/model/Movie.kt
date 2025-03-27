package com.salihakbas.movieappcompose.data.model

data class Movie(
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int,
    val popularity: Double,
    val genre_ids: List<Int>,
    val adult: Boolean,
    val video: Boolean
)

fun Movie.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = id,
        title = title,
        original_title = original_title,
        overview = overview,
        poster_path = poster_path,
        backdrop_path = backdrop_path,
        release_date = release_date,
        vote_average = vote_average,
        vote_count = vote_count,
        popularity = popularity,
        genre_ids = genre_ids,
        adult = adult,
        video = video
    )
}

