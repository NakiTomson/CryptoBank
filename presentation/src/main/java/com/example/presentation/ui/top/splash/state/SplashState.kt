package com.example.presentation.ui.top.splash.state

import com.example.presentation.core.BaseState

sealed class SplashState : BaseState {

    object Loading : SplashState()
    object Complete : SplashState()
    object Error : SplashState()
}