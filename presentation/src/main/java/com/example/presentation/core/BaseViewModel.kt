package com.example.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val scopeJob: Job = SupervisorJob()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    override val coroutineContext: CoroutineContext = scopeJob + Dispatchers.Main + errorHandler

    protected open val catchThrowable: (Throwable) -> Unit = {

    }

    protected fun BaseViewModel.viewModelScope(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(this@BaseViewModel.coroutineContext + context) { block.invoke(this) }

    protected fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
        catchThrowable.invoke(throwable)
    }

    override fun onCleared() {
        coroutineContext.cancelChildren()
        super.onCleared()
    }
}
