package com.example.presentation.navigation.sreens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import presentation.R


sealed class BottomNavigationScreens(
    val graph: String,
    @StringRes val titleResId: Int,
    val icon: ImageVector,
    val route: String
) {
    object Home : BottomNavigationScreens(
        graph = HomeScreens.getGraph(),
        titleResId = R.string.home,
        icon = Icons.Default.Home,
        route = HomeRoute
    )

    object Profile : BottomNavigationScreens(
        graph = ProfileScreens.getGraph(),
        titleResId = R.string.profile,
        icon = Icons.Default.Settings,
        route = ProfileRoute
    )

    companion object {

        private const val HomeRoute = "home_screen"
        private const val ProfileRoute = "profile_screen"
    }
}