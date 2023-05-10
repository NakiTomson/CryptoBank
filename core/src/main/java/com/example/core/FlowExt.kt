package com.example.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.stateInWhileSubscribed(
    initialValue: T,
    stopTimeoutMillis: Long = 5000,
    scope: CoroutineScope
): StateFlow<T> {
    return stateIn(
        scope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis),
        initialValue
    )
}
