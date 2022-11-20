package com.example.app.domain.useCases

import com.example.app.data.models.MovieData
import com.example.app.data.repositories.MoviesRepositoryImpl
import javax.inject.Inject

class LoadMovieByTitleUseCase @Inject constructor(private val repository: MoviesRepositoryImpl) {

    suspend fun loadMovie(title: String): MovieData {
        return repository.loadMovieByTitle(title)
    }
}