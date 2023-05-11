package com.example.presentation.ui.top.onboarding.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.core_compose.InternetConnectionLostScreen
import com.example.presentation.ui.top.onboarding.model.OnBoardingViewModel
import com.example.presentation.ui.top.splash.model.SplashViewModel
import com.example.presentation.ui.top.splash.screen.SplashRoute
import com.example.presentation.ui.top.splash.state.SplashState

@Composable
@Preview(showBackground = true)
private fun SplashScreenPreview() {
    OnBoardingScreen()
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun OnBoardingRoute(
    onBoardingFinished: () -> Unit = {},
    supportNetworkErrorScreen: Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    val error = viewModel.stateFlow.value.error.collectAsStateWithLifecycle()
    InternetConnectionLostScreen({ error.value }, {

    }, content = {
        OnBoardingRoute(onBoardingFinished, viewModel)
    })
}

@Composable
fun OnBoardingRoute(
    onBoardingFinished: () -> Unit = {},
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = viewModel, block = {

    })
    OnBoardingScreen()
}

@Composable
fun OnBoardingScreen(
    onBoardingFinished: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                modifier = Modifier.clickable { onBoardingFinished.invoke() },
                text = "OnBoarding",
                color = Color.Red,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
