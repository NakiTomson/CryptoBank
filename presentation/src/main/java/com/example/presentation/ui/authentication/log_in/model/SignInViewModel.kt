package com.example.presentation.ui.authentication.log_in.model

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.SavedStateHandle
import com.example.domain.api.UserInteractor
import com.example.entity.AuthorizationType
import com.example.entity.UserEntity
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.core.suspend
import com.example.presentation.ui.authentication.log_in.event.SignInSideEffect
import com.example.presentation.ui.authentication.log_in.state.SignInState
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import presentation.R
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userInteractor: UserInteractor,
    @ApplicationContext val context: Context,
) : StatefulScreenModel<SignInState, SideEffect>(SignInState()) {

    private val gso = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    private val mGoogleSignInClient = GoogleSignIn.getClient(context, gso)

    private val googleAccount = GoogleSignIn.getLastSignedInAccount(context)

    private val isAuthorized: Boolean
        get() = isGoogleAuthorized || isFireBaseAuthorized || isFacebookAuthorized

    private val isGoogleAuthorized: Boolean
        get() = getGoogleAuthorized() != null

    private val isFireBaseAuthorized: Boolean
        get() = getFirebaseAuthorized() != null

    private val isFacebookAuthorized: Boolean
        get() = getFacebookAuthorized() != null

    init {
        viewModelScope {
            verificationAuthorizedUser()
        }
    }

    private suspend fun verificationAuthorizedUser() {
        if (isAuthorized) {
            postSideEffect(SignInSideEffect.AuthorizationSuccess)
        } else {
            userInteractor.clearUser()
        }
    }

    private fun getFirebaseAuthorized(): UserEntity? {
        return UserEntity(
            Firebase.auth.currentUser?.tenantId ?: return null,
            Firebase.auth.currentUser?.email ?: return null,
            AuthorizationType.FireBase
        )
    }

    private fun getGoogleAuthorized(): UserEntity? {
        return UserEntity(
            googleAccount?.id ?: return null,
            googleAccount.email ?: return null,
            AuthorizationType.Google
        )
    }

    private fun getFacebookAuthorized(): UserEntity? {
        return null
    }

    suspend fun onGoogleAuthorizationClicked() {
        if (isGoogleAuthorized) {
            postSideEffect(SignInSideEffect.AuthorizationSuccess)
            return
        }
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        postSideEffect(SignInSideEffect.GoogleResultLauncher(signInIntent))
    }

    suspend fun onFacebookAuthorizationClicked() {
        if (isFireBaseAuthorized) {
            postSideEffect(SignInSideEffect.AuthorizationSuccess)
            return
        }
    }

    suspend fun onFireBaseAuthorizationClicked(email: String, password: String) {
        reduceState {
            getState().copy(isValidEmailValue = isValidEmail(email), isValidPasswordValue = isValidPassword(password))
        }
        if (isValidEmail(email) && isValidPassword(password)) fireBaseSignIn(email, password)
    }

    private suspend fun fireBaseSignIn(email: String, password: String) {
        try {
            val result = Firebase.auth.signInWithEmailAndPassword(email, password).suspend()
            with(result.user){
                userInteractor.saveUser(UserEntity(this?.tenantId.toString(), email, AuthorizationType.FireBase))
                postSideEffect(SignInSideEffect.AuthorizationSuccess)
            }
        } catch (exception: FirebaseAuthInvalidUserException) {
            reduceState {
                val alertState = getState().getAlert().copyDef(
                    isVisibleValue = true,
                    positiveSideEffect = SignInSideEffect.OpenSignUp,
                    titleValue = exception.message.toString(),
                    descriptionValue = exception.localizedMessage ?: "",
                    positiveValue = context.getString(R.string.sign_in),
                )
                getState().copy(alertStateValue = alertState)
            }
            postSideEffect(SignInSideEffect.AuthorizationError)
        } catch (exception: FirebaseAuthInvalidCredentialsException) {
            reduceState {
                val alertState = getState().getAlert().copyDef(
                    isVisibleValue = true,
                    titleValue = exception.message.toString(),
                    descriptionValue = exception.localizedMessage ?: "",
                )
                getState().copy(alertStateValue = alertState)
            }
            postSideEffect(SignInSideEffect.AuthorizationError)
        } catch (e: Exception) {
            postSideEffect(SignInSideEffect.AuthorizationError)
        }
    }

    suspend fun googleAuthorizationResult(result: ActivityResult) {
        reduceState { this.getState().copy(loadingValue = false) }
        if (result.resultCode == -1) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val user = task.getResult(ApiException::class.java)
                with(user){
                    userInteractor.saveUser(UserEntity(id!!, email!!, AuthorizationType.Google))
                    postSideEffect(SignInSideEffect.AuthorizationSuccess)
                }
            } catch (e: ApiException) {
                postSideEffect(SignInSideEffect.AuthorizationError)
            }
        } else {
            postSideEffect(SignInSideEffect.AuthorizationError)
        }
    }

    suspend fun onAlertDismiss() {
        onAlertCompiled()
    }

    suspend fun onAlertCompiled() {
        reduceState { getState().copy(alertStateValue = getState().getAlert().copyDef(isVisibleValue = false)) }
    }

    suspend fun onPositiveClicked(event: SideEffect?) {
        reduceState { getState().copy(alertStateValue = getState().getAlert().copyDef(isVisibleValue = false)) }
        delay(100)
        event?.let { postSideEffect(it) }
    }

     private fun isValidEmail(email: String?): Boolean =
        !(email.isNullOrBlank() || email.contains("@").not())

    private fun isValidPassword(password: String?): Boolean =
        !(password.isNullOrBlank() || password.length < 4)

}
