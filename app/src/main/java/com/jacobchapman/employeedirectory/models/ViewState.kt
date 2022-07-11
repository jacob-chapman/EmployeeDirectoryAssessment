package com.jacobchapman.employeedirectory.models

import com.jacobchapman.domain.DomainData

sealed class ViewState<T, E> {
    data class Loaded<T>(val viewData: T) : ViewState<T, Nothing>()
    data class Empty(val message: String) : ViewState<Nothing, Nothing>()
    data class Error<E>(val errorViewData: E?) : ViewState<Nothing, E>()
    object Loading : ViewState<Nothing, Nothing>()
}

fun <T : Any, R : Any, E : Any> DomainData<T, E>.toViewState(
    mapError: (E?) -> E? = { it },
    mapLoaded: (T) -> ViewState<R, *>
): ViewState<R, E> {
    return when (this) {
        is DomainData.Error -> ViewState.Error(mapError(this.body))
        is DomainData.Loaded -> mapLoaded(this.data)
        DomainData.Loading -> ViewState.Loading
    } as ViewState<R, E>
}