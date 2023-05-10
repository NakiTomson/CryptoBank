package com.example.presentation.navigation.nav_graph

import android.os.Parcelable
import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.presentation.navigation.addData
import com.example.presentation.navigation.navigateInclusive
import com.example.presentation.navigation.sreens.AuthenticationScreens
import com.example.presentation.navigation.sreens.TopScreens
import com.example.presentation.ui.top.navigation.screen.NavigationScreen
import com.example.presentation.ui.top.onboarding.screen.OnBoardingScreen
import com.example.presentation.ui.top.splash.screen.SplashRoute
import com.example.presentation.ui.top.splash.screen.SplashScreen
import kotlinx.parcelize.Parcelize

fun NavGraphBuilder.topNavGraph(
    navController: NavHostController
) {
    navigation(startDestination = TopScreens.Splash.route, route = TopScreens.getGraph()) {
        composable(route = TopScreens.Splash.route) {
            SplashRoute(onSplashFinished = {
                navController.navigateInclusive(TopScreens.OnBoarding.route, TopScreens.Splash.route)
            }, singInClicked = {
                navController.navigate(AuthenticationScreens.getGraph())
            })
        }
        composable(route = TopScreens.OnBoarding.route) {
            OnBoardingScreen(onBoardingFinished = {
                navController.navigateInclusive(TopScreens.Navigation.route, TopScreens.OnBoarding.route)
            })
        }
        composable(route = TopScreens.Navigation.route) {
            NavigationScreen(navController)
        }
    }
}

@Parcelize
data class Person(val name: String) : Parcelable

