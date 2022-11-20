package com.example.app.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.models.MovieData
import com.example.app.domain.useCases.SearchMoviesByTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val searchUseCase: SearchMoviesByTitleUseCase
) : ViewModel() {

    fun search(title: String, pages: Int) {
        viewModelScope.launch {
            searchUseCase.searchMovie(title, pages)
        }
    }
}

sealed class DetailsScreenState {
    object Init : DetailsScreenState()
    object Loading : DetailsScreenState()
    class Loaded(movies: MovieData) : DetailsScreenState()
}