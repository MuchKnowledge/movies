package com.example.app.data.mappers

import com.example.app.data.models.MovieData
import com.example.app.data.models.MovieResponse

fun MovieResponse.responseToData(): MovieData {
    return MovieData(
        id = id ?: "empty",
        title = title ?: "empty",
        year = year ?: "0",
        type = type ?: "movie",
        plot = plot ?: "desciprion",
        poster = poster ?: "",
        genre = genre ?: "",
        language = language ?: "",
        writer = writer ?: "",
        imdbRating = imdbRating ?: "",
    )
}
