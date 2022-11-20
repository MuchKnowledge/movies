package com.example.app.data.models

data class MovieData(
    val id: String,
    val title: String,
    val year: String,
    val poster: String,
    val isLiked: Boolean = false,
    val comments: List<String> = listOf(),
)