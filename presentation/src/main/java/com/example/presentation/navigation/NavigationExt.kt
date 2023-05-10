package com.example.presentation.navigation

import android.os.Parcelable
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination


fun NavHostController.navigateInclusive(route: String, popUpTo: String) {
    navigate(route = route) { popUpTo(popUpTo) { inclusive = true } }
}

fun NavHostController.navigateSingleTop(route: String) {
    navigate(route = route) { launchSingleTop = true }
}

fun NavHostController.navigateSaveState(route: String) {
    navigate(route) {
        popUpTo(this@navigateSaveState.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun <T> NavHostController.addData(vararg data: Pair<String, T> = emptyArray()): NavHostController {
    data.forEach { currentBackStackEntry?.savedStateHandle?.set(it.first, it.second) }
    return this
}

fun NavDestination?.isCurrentRoute(screenRoute: String): Boolean =
    this?.hierarchy?.any { it.route == screenRoute } == true

fun NavDestination?.isInCurrentGraph(graph: String): Boolean {
    return this?.hierarchy?.find { it.route?.contains(graph) == true } != null
}

