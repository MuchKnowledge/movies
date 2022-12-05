package com.example.app.domain.use_cases

import com.example.app.data.repositories.MoviesRepositoryImpl
import javax.inject.Inject

class UpdateLikeStatusUseCase @Inject constructor(private val repository: MoviesRepositoryImpl) {

    suspend fun update(id: String, isLiked: Boolean) {
        repository.updateMovieLikeStatus(id, isLiked)
    }
}