package com.example.presentation.ui.top.navigation.screen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.isInCurrentGraph
import com.example.presentation.navigation.sreens.BottomNavigationScreens
import com.example.presentation.navigation.nav_host.NavigationGraph
import com.example.presentation.navigation.navigateSaveState
import com.example.presentation.ui.top.navigation.model.NavigationViewModel
import com.example.presentation.ui.top.onboarding.model.OnBoardingViewModel

@Composable
@Preview(showBackground = true)
private fun NavigationScreenPreview() {
    NavigationScreen(rememberNavController(), viewModel = NavigationViewModel(SavedStateHandle()))
}

@Composable
fun NavigationScreen(topNavHostController: NavHostController, viewModel: NavigationViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = viewModel, block = {

    })
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        MainBottomNavigation(navController)
    }) { it ->
        NavigationGraph(navController, Modifier.padding(it), topNavHostController)
    }
}


@Composable
private fun MainBottomNavigation(navHostController: NavHostController) {
    val screens = listOf(BottomNavigationScreens.Home, BottomNavigationScreens.Profile)
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation {
        screens.forEach {
            AddItem(it, currentDestination, navHostController)
        }
    }
}


@Composable
private fun RowScope.AddItem(
    screen: BottomNavigationScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = screen.icon, contentDescription = null
            )
        },
        label = {
            Text(stringResource(screen.titleResId))
        },
        selected = currentDestination.isInCurrentGraph(screen.graph),
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigateSaveState(screen.graph)
        },
    )
}

