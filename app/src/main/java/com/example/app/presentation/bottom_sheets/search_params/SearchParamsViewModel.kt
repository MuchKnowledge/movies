package com.example.app.presentation.bottom_sheets.search_params

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchParamsViewModel @Inject constructor() : ViewModel() {

    val state: MutableLiveData<SearchParamsState> = MutableLiveData(SearchParamsState.QueryValidation())

    private var isTextValid = false
    private var isPageValid = false

    fun validatePage(page: String) {
        if (page.isNotEmpty()) {
            page.trim()
            isPageValid = true
            state.value = SearchParamsState.QueryValidation(isTextValid = isTextValid, isPageValid = isPageValid)
        }
    }

    fun validateText(text: String) {
        if (text.isNotEmpty()) {
            text.trim()
            isTextValid = true
            state.value = SearchParamsState.QueryValidation(isTextValid = isTextValid, isPageValid = isPageValid)
        }
    }
}
