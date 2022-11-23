package com.example.app.data.models

data class MovieData(
    val id: String,
    val title: String,
    val year: String,
    val type: String,
    val plot: String,
    val imdbRating: String?,
    val language: String?,
    val writer: String?,
    val genre: String?,
    val poster: String,
    var isLiked: Boolean = false,
)