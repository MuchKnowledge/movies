package com.example.app.data.models

sealed class ResponseResult<T> {
    class Success<T>(val data: T) : ResponseResult<T>()
    class Error<T>(val message: String) : ResponseResult<T>()
}