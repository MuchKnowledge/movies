package com.example.app.presentation.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.models.MovieData
import com.example.app.domain.use_cases.SearchMoviesByTitleUseCase
import com.example.app.domain.use_cases.UpdateLikeStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchUseCase: SearchMoviesByTitleUseCase,
    private val updateLikeStatusUseCase: UpdateLikeStatusUseCase,
) : ViewModel() {

    val state: MutableLiveData<MainScreenState> = MutableLiveData(MainScreenState.Init)

    fun search(title: String, page: Int) {
        viewModelScope.launch {
            state.value = MainScreenState.Loading
            val result = searchUseCase.searchMovie(title, page)

            when {
                result.isEmpty() -> state.value = MainScreenState.Empty
                else -> state.value = MainScreenState.Loaded(result)
            }
        }
    }

    fun updateLikeStatus(id: String, isLiked: Boolean) {
        viewModelScope.launch {
            updateLikeStatusUseCase.update(id, isLiked)
        }
    }

    fun onBackButtonClicked() {
        viewModelScope.launch {
            state.value = MainScreenState.Init
        }
    }
}

sealed class MainScreenState {
    object Init : MainScreenState()
    object Loading : MainScreenState()
    object Empty : MainScreenState()
    class Loaded(val movies: List<MovieData>) : MainScreenState()
}