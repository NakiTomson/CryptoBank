package com.example.presentation.ui.top.splash.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.core.EmptySideEffect
import com.example.presentation.core_compose.InternetConnectionLostScreen
import com.example.presentation.ui.top.splash.model.SplashViewModel
import com.example.presentation.ui.top.splash.state.SplashSideEffect
import com.example.presentation.ui.top.splash.state.SplashState
import presentation.R

@Composable
@Preview(showBackground = true)
private fun SplashScreenPreview() {
    SplashScreen()
}

@Composable
fun SplashRoute(
    openOnBoarding: () -> Unit = {},
    openRegistration: () -> Unit = {},
    openNavigation: () -> Unit = {},
    supportNetworkErrorScreen: Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    InternetConnectionLostScreen(state.value == SplashState.Error, {
        viewModel.tryLoadAgain()
    }, content = {
        SplashRoute(openOnBoarding, openRegistration, openNavigation)
    })
}


@Composable
fun SplashRoute(
    openOnBoarding: () -> Unit = {},
    openRegistration: () -> Unit = {},
    openNavigation: () -> Unit = {},
    viewModel: SplashViewModel = hiltViewModel()
) {
    val events = viewModel.sideEffectFlow.collectAsStateWithLifecycle(EmptySideEffect)

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.initSplashDelay()
    })

    LaunchedEffect(viewModel) {
        snapshotFlow { events.value }
            .collect {
                when (it) {
                    SplashSideEffect.OpenOnBoarding -> openOnBoarding.invoke()
                    SplashSideEffect.OpenRegistration -> openRegistration.invoke()
                    SplashSideEffect.OpenNavigation -> openNavigation.invoke()
                }
            }
    }
    SplashScreen()
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_interface),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

