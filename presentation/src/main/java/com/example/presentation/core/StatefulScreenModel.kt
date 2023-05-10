package com.example.presentation.core

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class StatefulScreenModel<STATE : Any, SIDE_EFFECT : Any>(initialState: STATE) :
    BaseViewModel() {

    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow = _stateFlow.asStateFlow()

    private val _sideEffectFlow = MutableSharedFlow<SIDE_EFFECT>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    suspend fun reduceState(block: (STATE) -> STATE) =
        launch(Dispatchers.Main) { _stateFlow.value = block(_stateFlow.value) }

    suspend fun postSideEffect(sideEffect: SIDE_EFFECT) =
        coroutineScope { launch(Dispatchers.Main) { _sideEffectFlow.emit(sideEffect) } }

    fun getCurrentState(): STATE {
       return stateFlow.value
    }
}