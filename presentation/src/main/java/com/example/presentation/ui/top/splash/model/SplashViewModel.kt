package com.example.presentation.ui.top.splash.model

import androidx.lifecycle.SavedStateHandle
import com.example.domain.api.UserInteractor
import com.example.errors.ServerError
import com.example.presentation.core.BaseState
import com.example.presentation.core.EmptyState
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.top.splash.state.SplashSideEffect
import com.example.presentation.ui.top.splash.state.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userInteractor: UserInteractor,
    private val dispatcher: com.example.core.Dispatchers,
) : StatefulScreenModel<BaseState, SideEffect>(EmptyState) {

    fun initSplashDelay() {
        viewModelScope {
            subscribeToken()
        }
    }

    fun tryLoadAgain() {
        viewModelScope {
            prepareToken()
        }
    }

    private suspend fun subscribeToken() {
        userInteractor.token.collect {
            if (it == null) {
                prepareToken()
                return@collect
            }
            onSplashTimerFinished()
        }
    }

    private suspend fun prepareToken() {
        try {
            reduceState { SplashState.Loading }
            userInteractor.prepareToken()
        } catch (e: ServerError) {
            reduceState { SplashState.Error }
        }
    }

    private suspend fun onSplashTimerFinished() {
        withContext(dispatcher.io) {
            delay(TIMER_DELAY)
            withContext(dispatcher.main) {
                reduceState { SplashState.Complete }
                when {
                    userInteractor.isNeedOnBoarding() -> postSideEffect(SplashSideEffect.OpenOnBoarding)
                    userInteractor.isNeedRegistration() -> postSideEffect(SplashSideEffect.OpenRegistration)
                    else -> postSideEffect(SplashSideEffect.OpenNavigation)
                }
            }
        }
    }


    companion object {
        const val TIMER_DELAY = 1000L
    }
}
