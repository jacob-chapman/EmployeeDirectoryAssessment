package com.jacobchapman.network.utils

import com.jacobchapman.domain.DomainData
import com.jacobchapman.network.models.ApiResponse

fun <R : Any, ER : Any, T : Any, E : Any> ApiResponse<R, ER>.toDomainData(
    onError: (ER) -> E,
    validateSuccess: (R) -> Boolean = { true },
    onSuccess: (R) -> T
): DomainData<T, E> {
    return when (this) {
        is ApiResponse.NetworkError -> DomainData.Error("", null) // todo
        is ApiResponse.ServerError -> DomainData.Error(this.code.toString(), null) // todo
        is ApiResponse.Success -> if (validateSuccess(this.body)) {
            DomainData.Loaded(onSuccess(this.body))
        } else {
            DomainData.Error("invalid response")
        }

    }
}