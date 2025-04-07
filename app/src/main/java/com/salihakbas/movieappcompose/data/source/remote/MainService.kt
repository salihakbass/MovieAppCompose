package com.salihakbas.movieappcompose.data.source.remote

import com.salihakbas.movieappcompose.data.model.movie.MovieCreditsResponse
import com.salihakbas.movieappcompose.data.model.movie.MovieDetailResponse
import com.salihakbas.movieappcompose.data.model.movie.MovieResponse
import com.salihakbas.movieappcompose.data.model.movie.MovieTrailerResponse
import com.salihakbas.movieappcompose.data.model.person.PersonDetailResponse
import com.salihakbas.movieappcompose.data.model.series.SeriesCreditsResponse
import com.salihakbas.movieappcompose.data.model.series.SeriesResponse
import com.salihakbas.movieappcompose.data.model.series.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("region") region: String? = null
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("region") region: String? = null
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("region") region: String? = null
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("region") region: String? = null
    ): MovieResponse

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvSeries(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("timezone") timezone: String? = null
    ): SeriesResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirSeries(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("timezone") timezone: String? = null
    ): SeriesResponse

    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("timezone") timezone: String? = null
    ): SeriesResponse

    @GET("tv/popular")
    suspend fun getTopRatedSeries(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("timezone") timezone: String? = null
    ): SeriesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("append_to_response") appendToResponse: String? = null
    ): MovieDetailResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ): MovieCreditsResponse

    @GET("tv/{series_id}")
    suspend fun getSerieDetail(
        @Path("series_id") seriesId: Int,
        @Query("language") language: String = "en-US",
        @Query("append_to_response") appendToResponse: String? = null
    ): TvShowResponse

    @GET("tv/{series_id}/credits")
    suspend fun getSerieCredits(
        @Path("series_id") seriesId: Int,
        @Query("language") language: String = "en-US"
    ): SeriesCreditsResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailer(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ): MovieTrailerResponse

    @GET("person/{person_id}")
    suspend fun getPersonDetail(
        @Path("person_id") personId: Int,
        @Query("append_to_response") appendToResponse: String? = null,
        @Query("language") language: String = "en-US"
    ): PersonDetailResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("tv/{series_id}/similar")
    suspend fun getSimilarSeries(
        @Path("series_id") seriesId: Int,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): SeriesResponse

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieResponse
}