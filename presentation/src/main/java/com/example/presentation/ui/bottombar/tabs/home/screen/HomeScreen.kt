package com.example.presentation.ui.bottombar.tabs.home.screen

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.presentation.ui.bottombar.tabs.home.model.HomeViewModel
import com.example.presentation.ui.top.navigation.model.NavigationViewModel


@Composable
fun HomeScreen(
    onHomeClicked: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel, block = {

    })

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier.clickable {
                onHomeClicked.invoke()
            },
            text = "Home",
            color = Color.Red,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    HomeScreen(viewModel = HomeViewModel(SavedStateHandle()))
}