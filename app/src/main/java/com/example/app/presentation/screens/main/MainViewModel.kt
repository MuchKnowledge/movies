package com.example.app.presentation.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.models.MovieData
import com.example.app.data.models.ResponseResult
import com.example.app.domain.use_cases.SearchMoviesByTitleUseCase
import com.example.app.domain.use_cases.UpdateLikeStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchUseCase: SearchMoviesByTitleUseCase,
) : ViewModel() {

    val state: MutableLiveData<MainScreenState> = MutableLiveData(MainScreenState.Init)

    fun search(title: String, page: Int) {
        viewModelScope.launch {
            state.value = MainScreenState.Loading
            when (val result = searchUseCase.searchMovie(title, page)) {
                is ResponseResult.Error -> state.value = MainScreenState.Error(result.message)
                is ResponseResult.Success -> state.value = MainScreenState.Loaded(result.data)
            }
        }
    }

    fun setInitPosition() {
        viewModelScope.launch {
            state.value = MainScreenState.Init
        }
    }
}

sealed class MainScreenState {
    object Init : MainScreenState()
    object Loading : MainScreenState()
    class Error(val message: String) : MainScreenState()
    class Loaded(val movies: List<MovieData>) : MainScreenState()
}