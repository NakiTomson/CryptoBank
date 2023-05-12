package com.example.presentation.ui.authentication.log_in.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.core_compose.InternetConnectionLostScreen
import com.example.presentation.theme.Black100
import com.example.presentation.ui.authentication.log_in.model.SignInViewModel
import com.example.presentation.ui.top.splash.screen.SplashRoute
import com.example.presentation.ui.top.splash.state.SplashState


@Composable
fun SignInRoute(
    singUpClicked: () -> Unit = {},
    supportNetworkErrorScreen: Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    InternetConnectionLostScreen({ state.value == SplashState.Error }, {

    }, content = {
        SignInRoute(singUpClicked, viewModel)
    }, Black100, Black100, Black100)

}


@Composable
fun SignInRoute(
    singUpClicked: () -> Unit = {},
    viewModel: SignInViewModel = hiltViewModel()
) {
    SignInScreen(singUpClicked)
}

@Composable
fun SignInScreen(
    singUpClicked: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                singUpClicked.invoke()
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            text = "SignIn",
            color = Color.Red,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun SignUpPreview() {
    SignInScreen()
}