package com.myapp.newyorktimes.network

sealed class ApiResult<T> {
    class Success<T>(val response: T) : ApiResult<T>()
    class Error<T>(val error: Throwable? = null) : ApiResult<T>()
}
