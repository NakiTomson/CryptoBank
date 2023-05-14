package com.example.presentation.ui.authentication.log_in.event

import android.content.Intent
import com.example.presentation.core.SideEffect

sealed class SignInSideEffect : SideEffect {

    class GoogleResultLauncher(val intent: Intent) : SignInSideEffect()
    object AuthorizationSuccess : SignInSideEffect()
    object AuthorizationError : SignInSideEffect()
    object OpenSignUp: SignInSideEffect()
}