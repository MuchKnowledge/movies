package com.example.app.presentation.screens.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.use_cases.LoadMovieByIdUseCase
import com.example.app.domain.use_cases.UpdateLikeStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val loadMovieByTitleUseCase: LoadMovieByIdUseCase,
    private val updateLikeStatusUseCase: UpdateLikeStatusUseCase,
) : ViewModel() {

    val state: MutableLiveData<DetailsScreenState> = MutableLiveData(DetailsScreenState.Loading)

    fun loadMovieById(id: String) {
        viewModelScope.launch {
            state.value = DetailsScreenState.Loading
            val result = loadMovieByTitleUseCase.loadMovie(id)
            state.value = DetailsScreenState.Loaded(result)
        }
    }
}