package com.example.presentation.core

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T> Task<T>.suspend(): T = suspendCancellableCoroutine<T> { continuation ->
    addOnCompleteListener { task ->
        continuation.invokeOnCancellation { it?.printStackTrace() }
        when {
            task.isSuccessful -> continuation.resume(task.result)
            task.isCanceled -> continuation.resumeWithException(CancellationException())
            task.exception != null -> continuation.resumeWithException(task.exception!!)
        }
    }
}