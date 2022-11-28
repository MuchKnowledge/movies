package com.example.app.data.repositories

import com.example.app.data.models.MovieData
import com.example.app.data.models.ResponseResult

interface MoviesRepository {
    suspend fun searchMoviesByTitle(title: String, page: Int): ResponseResult<List<MovieData>>
    suspend fun loadMovieById(title: String): MovieData
    suspend fun updateMovieLikeStatus(id: String, isLiked: Boolean)
}