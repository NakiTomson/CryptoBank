package com.example.presentation.core

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.core.launchUI
import com.example.core.withUIContext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class StatefulScreenModel<STATE : Any, SIDE_EFFECT : Any>(initialState: STATE) :
    ScreenModel {

    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow = _stateFlow.asStateFlow()

    private val _sideEffectFlow = MutableSharedFlow<SIDE_EFFECT>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit,
    ) = coroutineScope.launch(context, start, block)

    suspend fun reduceState(block: (STATE) -> STATE) =
        withUIContext { _stateFlow.value = block(_stateFlow.value) }

    suspend fun postSideEffect(sideEffect: SIDE_EFFECT) =
        coroutineScope { launchUI { _sideEffectFlow.emit(sideEffect) } }
}
