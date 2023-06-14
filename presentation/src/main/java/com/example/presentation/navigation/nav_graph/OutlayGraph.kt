package com.example.presentation.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.presentation.navigation.sreens.BottomNavigationScreens
import com.example.presentation.navigation.sreens.OutlayScreens
import com.example.presentation.ui.bottombar.tabs.outlay.screen.OutlayScreen
import com.example.presentation.ui.sreens.detail.screen.DetailScreen

fun NavGraphBuilder.outlayNavGraph(navController: NavHostController) {
    val startDestination = BottomNavigationScreens.Outlay
    navigation(startDestination = startDestination.route, route = OutlayScreens.getGraph()) {
        composable(BottomNavigationScreens.Outlay.route) {
            OutlayScreen()
        }
        composable(route = OutlayScreens.Detail.route) {
            DetailScreen()
        }
    }
}