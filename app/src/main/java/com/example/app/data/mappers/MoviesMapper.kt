package com.example.app.data.mappers

import com.example.app.data.db.entities.movie.MovieEntity
import com.example.app.data.models.MovieData
import com.example.app.data.models.MovieResponse

fun MovieResponse.responseToData(): MovieData {
    return MovieData(
        id = id ?: "empty",
        title = title ?: "empty",
        year = year ?: "0",
        poster = poster ?: ""
    )
}

fun MovieData.dataToEntity(): MovieEntity {
    return MovieEntity(
        movieId = id,
        isLiked = isLiked
    )
}