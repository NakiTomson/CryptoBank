package com.example.presentation.ui.authentication.log_in.event

import android.content.Intent
import com.example.presentation.core.SideEffect
import com.example.presentation.ui.top.onboarding.event.OnBoardingSideEffect

sealed class SignInSideEffect : SideEffect {

    class OpenResultLauncher(val intent: Intent) : SignInSideEffect()
    object AuthorizationSuccess : SignInSideEffect()
    object AuthorizationError : SignInSideEffect()
}