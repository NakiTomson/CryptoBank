package com.example.presentation.navigation.sreens

sealed class ProfileScreens(val route: String) {

    object Detail : ProfileScreens(DetailRoute)

    companion object {

        private const val ProfileGraph = "profile_graph"
        private const val DetailRoute = "$ProfileGraph/detail_screen"

        fun getGraph(): String = ProfileGraph
    }
}