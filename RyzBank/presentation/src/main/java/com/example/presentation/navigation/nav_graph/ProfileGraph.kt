package com.example.presentation.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.presentation.navigation.navigateSingleTop
import com.example.presentation.navigation.sreens.BottomNavigationScreens
import com.example.presentation.navigation.sreens.ProfileScreens
import com.example.presentation.ui.bottombar.tabs.profile.screen.ProfileScreen
import com.example.presentation.ui.sreens.detail.screen.DetailScreen


fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController
) {
    val startDestination = BottomNavigationScreens.Profile
    navigation(
        startDestination = startDestination.route,
        route = ProfileScreens.getGraph()
    ) {
        composable(BottomNavigationScreens.Profile.route) {
            ProfileScreen(onProfileClicked = {
                navController.navigateSingleTop(ProfileScreens.Detail.route)
            })
        }
        composable(ProfileScreens.Detail.route) {
            DetailScreen()
        }
    }
}