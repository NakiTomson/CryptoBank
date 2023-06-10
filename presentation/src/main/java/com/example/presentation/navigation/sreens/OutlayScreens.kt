package com.example.presentation.navigation.sreens

open class OutlayScreens(val route: String) {

    object Detail : OutlayScreens(DetailRoute)

    companion object {

        private const val OutlayGraph = "outlay_graph"
        private const val DetailRoute = "$OutlayGraph/detail_screen"

        fun getGraph(): String = OutlayGraph
    }
}