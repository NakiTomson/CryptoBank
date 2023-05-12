package com.example.presentation.ui.top.onboarding.event

import com.example.presentation.core.SideEffect

sealed class OnBoardingSideEffect : SideEffect {
    object OpenNavigation : OnBoardingSideEffect()
    object OpenRegister : OnBoardingSideEffect()
    object CheckPermission : OnBoardingSideEffect()
    object Back : OnBoardingSideEffect()
}