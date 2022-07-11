package com.jacobchapman.network.models

sealed class ApiResponse<out T : Any, out E : Any> {
    data class Success<T : Any>(val body: T) : ApiResponse<T, Nothing>()
    data class ServerError<E : Any>(val body: E?, val code: Int) : ApiResponse<Nothing, E>()
    data class NetworkError(val error: Throwable) : ApiResponse<Nothing, Nothing>()
}