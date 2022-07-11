package com.jacobchapman.core

import com.jacobchapman.domain.DomainData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

/**
 * Data Manager that handles fetching and potentially polling domain data objects.
 * This manager can be adapted to handle loading from disk or abstract away another internal data source.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DomainDataManager<T : Any, E : Any>(val fetchDelegate: suspend () -> DomainData<T, E>) {

    private val _refresh = MutableSharedFlow<Boolean>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    ).apply { tryEmit(false) }

    private var _cachedData: DomainData.Loaded<T>? = null

    fun observe(): Flow<DomainData<T, E>> {
        return _refresh.flatMapLatest { refresh ->
            flow {
                emit(DomainData.Loading)
                if (refresh || _cachedData == null) {
                    when (val delegateResponse = fetchDelegate()) {
                        is DomainData.Loaded -> {
                            _cachedData = delegateResponse
                            _cachedData?.let { emit(it) }
                        }
                        else -> emit(delegateResponse)
                    }
                } else {
                    _cachedData?.let { emit(it) }
                }
            }
        }
    }

    fun refresh() {
        _refresh.tryEmit(false)
    }
}