package com.marcgdiez.moviedbapp.data

import com.marcgdiez.moviedbapp.data.dto.GetMoviesResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("tv/popular")
    fun getMoviesList(
            @Query("api_key") api: String,
            @Query("language") language: String,
            @Query("page") page: Int
    ): Single<GetMoviesResponseDto>

    @GET("tv/{tv_id}/similar")
    fun getMoviesRecommendations(
            @Path("tv_id") id: Int,
            @Query("api_key") api: String,
            @Query("language") language: String
    ): Single<GetMoviesResponseDto>

}