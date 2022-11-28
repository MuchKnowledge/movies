package com.example.app.presentation.screens.main

import com.example.app.data.models.MovieData

sealed class MainScreenState {
    object Init : MainScreenState()
    object Loading : MainScreenState()
    class Error(val message: String) : MainScreenState()
    class Loaded(val movies: List<MovieData>) : MainScreenState()
}