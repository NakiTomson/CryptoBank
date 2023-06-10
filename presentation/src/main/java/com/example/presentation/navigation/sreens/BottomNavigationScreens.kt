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
    val icon: Int,
    val route: String
) {

    object Home : BottomNavigationScreens(
        graph = HomeScreens.getGraph(),
        titleResId = R.string.home,
        icon = R.drawable.ic_home,
        route = HomeRoute
    )

    object Outlay : BottomNavigationScreens(
        graph = OutlayScreens.getGraph(),
        titleResId = R.string.outlay,
        icon = R.drawable.ic_outlay,
        route = OutlayRoute
    )

    object Goal : BottomNavigationScreens(
        graph = GoalScreens.getGraph(),
        titleResId = R.string.goal,
        icon = R.drawable.ic_goal,
        route = GoalRoute
    )

    object Profile : BottomNavigationScreens(
        graph = ProfileScreens.getGraph(),
        titleResId = R.string.profile,
        icon = R.drawable.ic_profile,
        route = ProfileRoute
    )

    companion object {

        private const val HomeRoute = "home_screen"
        private const val OutlayRoute = "outlay_screen"
        private const val GoalRoute = "goal_screen"
        private const val ProfileRoute = "profile_screen"
    }
}