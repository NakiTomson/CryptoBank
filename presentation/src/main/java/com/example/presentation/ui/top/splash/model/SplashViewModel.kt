package com.example.presentation.ui.top.splash.model

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import com.example.domain.api.UserInteractor
import com.example.presentation.core.BaseState
import com.example.presentation.core.EmptyState
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.top.splash.state.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userInteractor: UserInteractor,
) : StatefulScreenModel<BaseState, SideEffect>(EmptyState) {

    init {
        viewModelScope {
            subscribeToken()
        }
    }

    private suspend fun subscribeToken() {
        userInteractor.token.collect {
            if (it == null) {
                prepareToken()
                return@collect
            }
            reduceState { SplashState.Complete }
        }
    }

    private suspend fun prepareToken() {
        try {
            reduceState { SplashState.Loading }
            userInteractor.prepareToken()
        } catch (e: Exception) {
            reduceState { SplashState.Error }
        }
    }

}
