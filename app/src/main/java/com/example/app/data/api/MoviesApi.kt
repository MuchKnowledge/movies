package com.example.app.data.api

import com.example.app.data.models.MovieResponse
import com.example.app.data.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("www.omdbapi.com/")
    suspend fun searchMoviesByTitle(
        @Query("s") searchQuery: String,
        @Query("page") page: Int,
        @Query("apikey") apiKey: String = NETWORK_API_KEY,
    ): SearchResponse

    @GET("www.omdbapi.com/")
    suspend fun loadMovieById(
        @Query("i") id: String,
        @Query("apikey") apiKey: String = NETWORK_API_KEY,
    ): MovieResponse
}