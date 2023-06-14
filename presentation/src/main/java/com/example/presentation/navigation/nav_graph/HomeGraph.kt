package com.example.presentation.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.presentation.navigation.navigateSaveState
import com.example.presentation.navigation.navigateSingleTop
import com.example.presentation.navigation.sreens.BottomNavigationScreens
import com.example.presentation.navigation.sreens.HomeScreens
import com.example.presentation.ui.bottombar.tabs.home.screen.HomeRoute
import com.example.presentation.ui.sreens.detail.screen.DetailScreen


fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    val startDestination = BottomNavigationScreens.Home
    navigation(startDestination = startDestination.route, route = HomeScreens.getGraph()) {
        composable(BottomNavigationScreens.Home.route) {
            HomeRoute(onDetailCardClicked = {
                navController.navigateSingleTop(HomeScreens.Detail.route)
            }, onAddNewCardClicked = {

            }, openProfileClicked = {
                navController.navigateSaveState(BottomNavigationScreens.Profile.graph)
            })
        }
        composable(route = HomeScreens.Detail.route) {
            DetailScreen()
        }
    }
}