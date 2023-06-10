package com.example.presentation.navigation.sreens

open class GoalScreens(val route: String) {

    object Detail : GoalScreens(DetailRoute)

    companion object {

        private const val GoalGraph = "goal_graph"
        private const val DetailRoute = "$GoalGraph/detail_screen"

        fun getGraph(): String = GoalGraph
    }
}