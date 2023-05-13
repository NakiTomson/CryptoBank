package com.example.presentation.ui.authentication.log_in.model

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.SavedStateHandle
import com.example.domain.api.UserInteractor
import com.example.presentation.core.BaseState
import com.example.presentation.core.EmptyState
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.authentication.log_in.event.SignInSideEffect
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userInteractor: UserInteractor,
    @ApplicationContext context: Context,
) : StatefulScreenModel<BaseState, SideEffect>(EmptyState) {

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()
    private val mGoogleSignInClient = GoogleSignIn.getClient(context, gso)


    suspend fun onGoogleAuthorizationClicked() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        postSideEffect(SignInSideEffect.OpenResultLauncher(signInIntent))
    }

    suspend fun handleAttemptToAuthorization(result: ActivityResult) {
        if (result.resultCode == -1) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                task.getResult(ApiException::class.java)
                userInteractor.setNeedRegistration(false)
                postSideEffect(SignInSideEffect.AuthorizationSuccess)
                return
            } catch (e: ApiException) {

            }
        }
        postSideEffect(SignInSideEffect.AuthorizationError)
    }
}