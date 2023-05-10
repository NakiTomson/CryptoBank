package com.example.presentation.ui.top.splash.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.ui.top.splash.model.SplashViewModel

@Composable
@Preview(showBackground = true)
private fun SplashScreenPreview() {
    SplashScreen()
}

@Composable
fun SplashRoute(
    onSplashFinished: () -> Unit = {},
    singInClicked: () -> Unit = {},
    viewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = viewModel, block = {

    })
    SplashScreen(onSplashFinished, singInClicked)
}

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit = {},
    singInClicked: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                modifier = Modifier.clickable { onSplashFinished.invoke() },
                text = "Splash",
                color = Color.Red,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                modifier = Modifier.clickable { singInClicked.invoke() },
                text = "Registration",
                color = Color.Red,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

