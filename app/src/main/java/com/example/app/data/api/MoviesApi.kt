package com.example.app.data.api

import com.example.app.data.models.MovieResponse
import com.example.app.data.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MoviesApi {

    @GET("/")
    suspend fun searchMoviesByTitle(
        @Query("s") searchQuery: String,
        @Query("page") page: Int,
        @Query("apikey") apiKey: String = "fade9ea2",
    ): SearchResponse

    @GET("/")
    suspend fun loadMovieById(
        @Query("i") id: String,
        @Query("apikey") apiKey: String = "fade9ea2",
    ): MovieResponse
}