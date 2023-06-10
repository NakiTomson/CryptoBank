package com.example.presentation.ui.bottombar.tabs.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.entity.UserEntity
import com.example.presentation.theme.White200
import com.example.presentation.ui.bottombar.tabs.home.model.HomeViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun HomeRoute(
    onHomeClicked: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    val userState = state.value.user.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel, block = {

    })
    HomeScreen(onHomeClicked) { userState.value }
}

@Composable
fun HomeScreen(
    onHomeClicked: () -> Unit = {},
    user: () -> UserEntity? = { null }
) {
    Box(modifier = Modifier.fillMaxSize().background(White200), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier.clickable { onHomeClicked.invoke() },
            text = "Home Screen",
            color = Color.Red,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}
