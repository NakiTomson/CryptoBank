package com.example.presentation.navigation.sreens


sealed class TopScreens(val route: String) {

    object Splash : TopScreens(SplashRoute)
    object OnBoarding : TopScreens(OnBoardingRoute)
    object Navigation : TopScreens(NavigationRoute)

    companion object {

        private const val SplashRoute = "splash_screen"
        private const val OnBoardingRoute = "on_boarding_screen"
        private const val NavigationRoute = "navigation_screen"

        private const val TopGraph = "top_graph"

        fun getGraph(): String = TopGraph
    }
}