package com.example.app.domain.use_cases

import com.example.app.data.models.MovieData
import com.example.app.data.models.ResponseResult
import com.example.app.data.repositories.MoviesRepositoryImpl
import javax.inject.Inject

class SearchMoviesByTitleUseCase @Inject constructor(private val repository: MoviesRepositoryImpl) {

    suspend fun searchMovie(title: String, page: Int): ResponseResult<List<MovieData>> {
        return repository.searchMoviesByTitle(title, page)
    }
}