package com.example.presentation.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.presentation.navigation.navigateInclusive
import com.example.presentation.navigation.sreens.AuthenticationScreens
import com.example.presentation.navigation.sreens.TopScreens
import com.example.presentation.ui.top.navigation.screen.NavigationScreen
import com.example.presentation.ui.top.onboarding.screen.OnBoardingRoute
import com.example.presentation.ui.top.splash.screen.SplashRoute

fun NavGraphBuilder.topNavGraph(
    navController: NavHostController
) {
    navigation(startDestination = TopScreens.Splash.route, route = TopScreens.getGraph()) {
        composable(route = TopScreens.Splash.route) {
            SplashRoute(openOnBoardingRoute = {
                navController.navigate(TopScreens.OnBoarding.route)
            }, openRegistrationRoute = {
                navController.navigate(AuthenticationScreens.getGraph())
            }, openNavigationRout = {
                navController.navigateInclusive(TopScreens.Navigation.route, TopScreens.OnBoarding.route)
            }, supportNetworkErrorScreen = Unit)
        }
        composable(route = TopScreens.OnBoarding.route) {
            OnBoardingRoute(openRegisterRoute = {
                navController.navigate(AuthenticationScreens.getGraph())
            }, openNavigationRoute = {
                navController.navigateInclusive(TopScreens.Navigation.route, TopScreens.OnBoarding.route)
            }, popBackStack = {
                navController.popBackStack()
            }, supportNetworkErrorScreen = Unit)
        }
        composable(route = TopScreens.Navigation.route) {
            NavigationScreen(navController)
        }
    }
}

