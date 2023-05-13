package com.example.presentation.ui.authentication.log_in.model

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.lifecycle.SavedStateHandle
import com.example.domain.api.UserInteractor
import com.example.entity.UserEntity
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.authentication.log_in.event.SignInSideEffect
import com.example.presentation.ui.authentication.log_in.state.SignInState
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userInteractor: UserInteractor,
    @ApplicationContext context: Context,
) : StatefulScreenModel<SignInState, SideEffect>(SignInState()) {

    private val gso = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    private val mGoogleSignInClient = GoogleSignIn.getClient(context, gso)

    private val googleAccount = GoogleSignIn.getLastSignedInAccount(context)

    private val isAuthorized: Boolean
        get() = isGoogleAuthorized || isFireBaseAuthorized

    private val isGoogleAuthorized: Boolean
        get() = getGoogleAuthorized() != null

    private val isFireBaseAuthorized: Boolean
        get() = getFirebaseAuthorized() != null

    init {
        viewModelScope {
            verificationAuthorizedUser()
        }
    }

    private suspend fun verificationAuthorizedUser() {
        if (isAuthorized) {
            viewModelScope {
                postSideEffect(SignInSideEffect.AuthorizationSuccess)
            }
        }
    }


    private fun getFirebaseAuthorized(): UserEntity? {
        return UserEntity(
            Firebase.auth.currentUser?.tenantId ?: return null,
            Firebase.auth.currentUser?.email ?: return null,
            Firebase.javaClass.typeName
        )
    }

    private fun getGoogleAuthorized(): UserEntity? {
        return UserEntity(
            googleAccount?.id ?: return null,
            googleAccount.email ?: return null,
            googleAccount.account?.type ?: return null
        )
    }

    suspend fun onGoogleAuthorizationClicked() {
        if (isGoogleAuthorized) {
            postSideEffect(SignInSideEffect.AuthorizationSuccess)
            return
        }
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        postSideEffect(SignInSideEffect.OpenResultLauncher(signInIntent))
    }

    suspend fun handleAuthorizationResult(result: ActivityResult) {
        reduceState { this.getCurrentState().copy(loadingValue = true) }
        if (result.resultCode == -1) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                task.getResult(ApiException::class.java)
                reduceState { this.getCurrentState().copy(loadingValue = false) }
                postSideEffect(SignInSideEffect.AuthorizationSuccess)
                return
            } catch (e: ApiException) {

            }
        }
        postSideEffect(SignInSideEffect.AuthorizationError)
    }

    suspend fun onFacebookAuthorizationClicked() {
        postSideEffect(SignInSideEffect.AuthorizationError)
    }

    suspend fun onFireBaseAuthorizationClicked(email: String?, password: String?) {
        reduceState {
            this.getCurrentState().copy(emailErrorValue = isValidEmail(email).not(), passwordErrorValue = isValidPassword(password).not())
        }


    }

    private fun isValidEmail(email: String?): Boolean =
        !(email.isNullOrBlank() || email.contains("@").not())

    private fun isValidPassword(password: String?): Boolean =
        !(password.isNullOrBlank() || password.length < 4)
}
