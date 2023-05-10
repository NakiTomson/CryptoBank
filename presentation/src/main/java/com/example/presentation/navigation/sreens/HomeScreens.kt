package com.example.presentation.navigation.sreens

sealed class HomeScreens(val route: String) {

    object Detail : HomeScreens(DetailRoute)

    companion object {

        private const val HomeGraph = "home_graph"
        private const val DetailRoute = "$HomeGraph/detail_screen"

        fun getGraph(): String = HomeGraph
    }
}