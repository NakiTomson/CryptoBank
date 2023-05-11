package com.example.presentation.ui.top.splash.state

import com.example.presentation.core.SideEffect


sealed class SplashSideEffect : SideEffect {

    object OpenOnBoarding : SplashSideEffect()
    object OpenNavigation : SplashSideEffect()
}