package com.jacobchapman.domain

sealed class DomainData<out T : Any, out E : Any> {
    data class Loaded<T : Any>(val data: T) : DomainData<T, Nothing>()
    object Loading : DomainData<Nothing, Nothing>()
    data class Error<E : Any>(val message: String, val body: E? = null) : DomainData<Nothing, E>()
}

fun <T : Any, E : Any, R> DomainData<T, E>.mapLoaded(mapper: (T) -> R) = when (this) {
    is DomainData.Loaded -> mapper(data)
    else -> null
}

fun <T: Any> DomainData<T, *>.getOrNull() = (this as? DomainData.Loaded<T>)?.data