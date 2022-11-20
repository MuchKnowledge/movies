package com.example.app.data.repositories

import com.example.app.data.api.MoviesApi
import com.example.app.data.db.dao.CommentDao
import com.example.app.data.db.dao.MoviesDao
import com.example.app.data.mappers.responseToData
import com.example.app.data.models.MovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesDao: MoviesDao,
    private val commentDao: CommentDao,
) : MoviesRepository {

    override suspend fun searchMoviesByTitle(title: String, page: Int): List<MovieData> {
        return withContext(Dispatchers.IO) {
            moviesApi.searchMoviesByTitle(title, page).movies.map { it.responseToData() }
        }
    }

    override suspend fun loadMovieByTitle(title: String): MovieData {
        return moviesApi.loadMovieByTitle(title).responseToData()
    }
}