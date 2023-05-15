package com.example.presentation.ui.authentication.log_in.screen

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.core.EmptySideEffect
import com.example.presentation.core_compose.CircularProgressBar
import com.example.presentation.core_compose.CustomAlert
import com.example.presentation.core_compose.InternetConnectionLostScreen
import com.example.presentation.core_compose.rememberCustomAlertState
import com.example.presentation.theme.Black100
import com.example.presentation.theme.Black300
import com.example.presentation.theme.White100
import com.example.presentation.ui.authentication.log_in.event.SignInSideEffect
import com.example.presentation.ui.authentication.log_in.model.SignInViewModel
import kotlinx.coroutines.launch
import presentation.R

@Composable
@Preview(showBackground = true, device = "id:pixel_6_pro")
private fun SignInPreview() {
    LogInScreen(modifier = Modifier.background(Black300))
}

@Composable
fun LogInRoute(
    authorizationSuccess: () -> Unit = {},
    createNewAccountClicked: () -> Unit = {},
    forgotPasswordClicked: () -> Unit = {},
    supportNetworkErrorScreen: Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    val isError = state.value.isError.collectAsStateWithLifecycle()
    InternetConnectionLostScreen({ isError.value }, {
    }, content = {
        LogInRoute(authorizationSuccess, createNewAccountClicked, forgotPasswordClicked, viewModel)
    }, navigationBarColor = Black100, statusBarColor = Black300)
}


@Composable
fun LogInRoute(
    authorizationSuccess: () -> Unit = {},
    createNewAccountClicked: () -> Unit = {},
    onForgotPasswordClicked: () -> Unit = {},
    viewModel: SignInViewModel = hiltViewModel()
) {
    val events = viewModel.sideEffectFlow.collectAsStateWithLifecycle(EmptySideEffect)
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    val isLoading = state.value.isLoading.collectAsStateWithLifecycle()
    val isValidEmail = state.value.isValidEmail.collectAsStateWithLifecycle()
    val isValidPassword = state.value.isValidPassword.collectAsStateWithLifecycle()
    val alertState = state.value.alertState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    val rememberAlertState =
        rememberCustomAlertState(state = alertState.value,
            onDismiss = {
                coroutineScope.launch { viewModel.onAlertDismiss() }
            }, onPositive = {
                coroutineScope.launch { viewModel.onPositiveClicked(it) }
            }, onCompiled = {
                coroutineScope.launch { viewModel.onAlertCompiled() }
            })

    val googleAuthorizationLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            coroutineScope.launch { viewModel.googleAuthorizationResult(it) }
        }

    LaunchedEffect(viewModel) {
        snapshotFlow { events.value }
            .collect {
                when (it) {
                    is SignInSideEffect.GoogleResultLauncher -> googleAuthorizationLauncher.launch(it.intent)
                    is SignInSideEffect.AuthorizationSuccess -> authorizationSuccess.invoke()
                    is SignInSideEffect.OpenSignUp -> createNewAccountClicked.invoke()
                    is SignInSideEffect.AuthorizationError -> {}
                }
            }
    }

    val logInModifier = Modifier
        .background(Black300)
        .fillMaxSize()

    LogInScreen(
        modifier = logInModifier,
        isLoading = { isLoading.value },
        createNewAccountClicked = createNewAccountClicked,
        onForgotPasswordClicked = onForgotPasswordClicked,
        onGoogleAuthorizationClicked = {
            coroutineScope.launch { viewModel.onGoogleAuthorizationClicked() }
        },
        onFireBaseAuthorizationClicked = { email, pass ->
            coroutineScope.launch { viewModel.onFireBaseAuthorizationClicked(email, pass) }
        },
        onFacebookAuthorizationClicked = {
            coroutineScope.launch { viewModel.onFacebookAuthorizationClicked() }
        },
        isValidEmail = { isValidEmail.value },
        isValidPassword = { isValidPassword.value },
    )
    CustomAlert(rememberAlertState)
}


@Composable
fun LogInScreen(
    modifier: Modifier = Modifier,
    isLoading: () -> Boolean = { false },
    createNewAccountClicked: () -> Unit = {},
    onForgotPasswordClicked: () -> Unit = {},
    onGoogleAuthorizationClicked: () -> Unit = {},
    onFireBaseAuthorizationClicked: (String, String) -> Unit = { email, pass -> },
    onFacebookAuthorizationClicked: () -> Unit = {},
    isValidEmail: () -> Boolean = { false },
    isValidPassword: () -> Boolean = { false },
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopCenter)
                .padding(top = 30.dp)
        )
        FormAuthorization(
            createNewAccountClicked,
            onForgotPasswordClicked,
            onGoogleAuthorizationClicked,
            onFireBaseAuthorizationClicked,
            onFacebookAuthorizationClicked,
            isValidEmail,
            isValidPassword
        )
        CircularProgressBar(
            Modifier.align(Alignment.Center),
            isLoading = isLoading,
            colorProgress = White100,
            colorIndicator = Black300
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FormAuthorization(
    createNewAccountClicked: () -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onGoogleAuthorizationClicked: () -> Unit,
    onFireBaseAuthorizationClicked: (String, String) -> Unit,
    onFacebookAuthorizationClicked: () -> Unit = {},
    isValidEmail: () -> Boolean,
    isValidPassword: () -> Boolean,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var password by remember { mutableStateOf(TextFieldValue("")) }
        var email by remember { mutableStateOf(TextFieldValue("")) }

        Text(
            text = stringResource(R.string.login),
            color = Color.White,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
                .drawWithCache {
                    onDrawBehind {
                        drawPath(getDrawBackgroundPath(this@drawWithCache), color = Black100)
                    }
                },
        )

        TextField(
            value = email,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(text = stringResource(R.string.email), color = White100)
            },
            isError = isValidEmail.invoke().not(),
            onValueChange = { email = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = White100,
                unfocusedIndicatorColor = Black300,
                containerColor = Color.Transparent,
                errorIndicatorColor = Color.Red,
                textColor = White100,
            ),
            maxLines = 1,
        )

        TextField(
            value = password,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 40.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = {
                Text(text = stringResource(R.string.password), color = White100)
            },
            onValueChange = { it ->
                password = it
            },
            isError = isValidPassword.invoke().not(),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = White100,
                unfocusedIndicatorColor = Black300,
                containerColor = Color.Transparent,
                errorIndicatorColor = Color.Red,
                textColor = White100
            ),
            trailingIcon = {
                Text(
                    text = stringResource(R.string.forgot),
                    modifier = Modifier.clickable {
                        onForgotPasswordClicked.invoke()
                    },
                    color = Color.White,
                    maxLines = 1
                )
            },
            maxLines = 1,
        )
        Button(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 45.dp)
                .height(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Black300),
            onClick = {
                onFireBaseAuthorizationClicked.invoke(email.text, password.text)
            }
        ) {
            Text(text = stringResource(R.string.log_in), color = Color.White, maxLines = 1)
        }

        Text(
            text = stringResource(R.string.or_continue),
            color = Black300,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        AuthorizationMethods(
            Modifier.padding(bottom = 20.dp),
            onGoogleAuthorizationClicked,
            onFacebookAuthorizationClicked
        )

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Black300, fontSize = 16.sp)) {
                    append(stringResource(R.string.not_have_account) + " ")
                }
                withStyle(style = SpanStyle(color = Color.White, fontSize = 16.sp)) {
                    append(stringResource(R.string.create_now))
                }
            },
            modifier = Modifier
                .padding(bottom = 20.dp)
                .clickable {
                    createNewAccountClicked.invoke()
                }
        )
    }
}

@Composable
private fun AuthorizationMethods(
    modifier: Modifier,
    onGoogleAuthorizationClicked: () -> Unit = {},
    onFacebookAuthorizationClicked: () -> Unit = {},
) {
    Row(modifier.padding(bottom = 20.dp)) {
        OutlinedButton(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 8.dp)
                .weight(1f)
                .height(50.dp),
            onClick = {
                onGoogleAuthorizationClicked.invoke()
            },
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Black100),
            border = BorderStroke(2.dp, Black300)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .padding(end = 14.dp)
                    .size(18.dp)
            )
            Text(text = stringResource(R.string.google), color = Black300, maxLines = 1)
        }

        OutlinedButton(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 16.dp)
                .height(50.dp)
                .weight(1f),
            onClick = {
                onFacebookAuthorizationClicked.invoke()
            },
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Black100),
            border = BorderStroke(2.dp, Black300)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .padding(end = 14.dp)
                    .size(24.dp)
            )
            Text(text = stringResource(R.string.facebook), color = Black300, maxLines = 1)
        }
    }
}


fun getDrawBackgroundPath(cacheDrawScope: CacheDrawScope): Path {
    with(cacheDrawScope) {
        val rect = RoundRect(
            rect = Rect(
                offset = Offset(0f, -50f),
                size = Size(size.width, size.width * 10),
            ),
            topLeft = CornerRadius(size.width / 10, size.width / 10),
            topRight = CornerRadius(size.width / 10, size.width / 10)
        )
        val path = Path()
        path.addRoundRect(rect)
        path.close()
        return path
    }
}
