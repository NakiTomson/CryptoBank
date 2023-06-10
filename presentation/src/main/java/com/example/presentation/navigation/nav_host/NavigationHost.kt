package com.example.presentation.navigation.nav_host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.presentation.navigation.nav_graph.goalNavGraph
import com.example.presentation.navigation.nav_graph.homeNavGraph
import com.example.presentation.navigation.nav_graph.outlayNavGraph
import com.example.presentation.navigation.nav_graph.profileNavGraph
import com.example.presentation.navigation.sreens.HomeScreens


@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier,
    topNavHostController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreens.getGraph(),
        modifier = modifier
    ) {
        homeNavGraph(navController = navController)
        outlayNavGraph(navController = navController)
        goalNavGraph(navController = navController)
        profileNavGraph(navController = navController)
    }
}