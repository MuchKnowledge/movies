package com.example.app.data.repositories

import com.example.app.data.api.MoviesApi
import com.example.app.data.mappers.responseToData
import com.example.app.data.models.MovieData
import com.example.app.data.models.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
) : MoviesRepository {

    override suspend fun searchMoviesByTitle(title: String, page: Int): ResponseResult<List<MovieData>> {
        return withContext(Dispatchers.IO) {
            val result = moviesApi.searchMoviesByTitle(title, page)
            when {
                result.errorText != null -> ResponseResult.Error(result.errorText)
                else -> ResponseResult.Success(result.movies!!.map { it.responseToData() })
            }
        }
    }

    override suspend fun loadMovieById(title: String): MovieData {
        return moviesApi.loadMovieById(title).responseToData()
    }
}