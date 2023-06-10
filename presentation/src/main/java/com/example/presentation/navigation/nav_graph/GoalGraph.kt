package com.example.presentation.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.presentation.navigation.navigateSingleTop
import com.example.presentation.navigation.sreens.BottomNavigationScreens
import com.example.presentation.navigation.sreens.GoalScreens
import com.example.presentation.navigation.sreens.HomeScreens
import com.example.presentation.ui.bottombar.tabs.goal.screen.GoalScreen
import com.example.presentation.ui.bottombar.tabs.home.screen.HomeRoute
import com.example.presentation.ui.sreens.detail.screen.DetailScreen

fun NavGraphBuilder.goalNavGraph(navController: NavHostController) {
    val startDestination = BottomNavigationScreens.Goal
    navigation(startDestination = startDestination.route, route = GoalScreens.getGraph()) {
        composable(BottomNavigationScreens.Goal.route) {
            GoalScreen()
        }
        composable(route = GoalScreens.Detail.route) {
            DetailScreen()
        }
    }
}