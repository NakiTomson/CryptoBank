package com.example.presentation.navigation.nav_host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.presentation.navigation.nav_graph.authenticationNavGraph
import com.example.presentation.navigation.nav_graph.topNavGraph
import com.example.presentation.navigation.sreens.TopScreens

@Composable
fun SetupRootNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = TopScreens.getGraph(), route = ROOT_ROUTE) {
        topNavGraph(navController)
        authenticationNavGraph(navController)
    }
}

private const val ROOT_ROUTE = "root"
