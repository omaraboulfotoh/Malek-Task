package com.example.movieapptask.network

import com.example.movieappkotlin.model.TmdbResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey:String, @Query("page") page:String): TmdbResponse?
}