package com.example.app.presentation.screens.details

import com.example.app.data.models.MovieData

sealed class DetailsScreenState {
    object Loading : DetailsScreenState()
    class Loaded(val movie: MovieData) : DetailsScreenState()
}