package com.example.presentation.ui.bottombar.tabs.outlay.screen

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
import com.example.presentation.ui.bottombar.tabs.goal.model.GoalViewModel
import com.example.presentation.ui.bottombar.tabs.goal.screen.GoalScreen
import com.example.presentation.ui.bottombar.tabs.outlay.model.OutlayViewModel
import com.example.presentation.ui.bottombar.tabs.profile.model.ProfileViewModel

@Composable
@Preview(showBackground = true)
private fun OutlayScreenPreview() {
    OutlayScreen(GoalViewModel(SavedStateHandle()))
}


@Composable
fun OutlayScreen(
    viewModel: GoalViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = viewModel, block = {

    })

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier.clickable {

            },
            text = "OutlayScreen",
            color = Color.Red,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}