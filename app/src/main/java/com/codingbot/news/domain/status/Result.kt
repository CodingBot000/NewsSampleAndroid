package com.codingbot.news.domain.status

sealed class Result<out T> {
    class Success<T>(val data: T): Result<T>()
    class ApiError<T>(val data: T): Result<T>()
    object Error : Result<Nothing>()
}