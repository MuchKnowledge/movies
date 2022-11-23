package com.example.app.domain.use_cases

import com.example.app.data.models.MovieData
import com.example.app.data.repositories.MoviesRepositoryImpl
import javax.inject.Inject

class LoadMovieByIdUseCase @Inject constructor(private val repository: MoviesRepositoryImpl) {

    suspend fun loadMovie(id: String): MovieData {
        return repository.loadMovieById(id)
    }
}