package com.example.app.presentation.bottom_sheets.search_params

sealed class SearchParamsState {
    class QueryValidation(val isTextValid: Boolean = false, val isPageValid: Boolean = false) : SearchParamsState()
}