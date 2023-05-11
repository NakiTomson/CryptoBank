package com.example.core

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatchers {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val compute: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}
