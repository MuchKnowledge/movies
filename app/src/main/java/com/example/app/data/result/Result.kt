package com.example.app.data.result

sealed class ResultData<T> {
    class Success<T>(data: T) : ResultData<T>()
    class Failed<T>(message: String) : ResultData<T>()
}