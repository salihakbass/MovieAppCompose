package com.salihakbas.movieappcompose.data.source.remote

import com.salihakbas.movieappcompose.data.model.MovieCreditsResponse
import com.salihakbas.movieappcompose.data.model.MovieDetailResponse
import com.salihakbas.movieappcompose.data.model.MovieResponse
import com.salihakbas.movieappcompose.data.model.SeriesResponse
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
}