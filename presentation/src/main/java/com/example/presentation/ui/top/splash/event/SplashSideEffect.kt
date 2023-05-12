package com.example.presentation.ui.top.splash.event

import com.example.presentation.core.SideEffect


sealed class SplashSideEffect : SideEffect {

    object OpenOnBoarding : SplashSideEffect()
    object OpenRegistration : SplashSideEffect()
    object OpenNavigation : SplashSideEffect()
}