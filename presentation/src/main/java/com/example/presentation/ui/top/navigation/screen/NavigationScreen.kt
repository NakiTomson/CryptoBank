package com.example.presentation.ui.top.navigation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.presentation.theme.White100
import com.example.presentation.ui.top.navigation.model.NavigationViewModel
import com.example.presentation.ui.top.onboarding.model.OnBoardingViewModel
import presentation.R

@Composable
@Preview(showBackground = true)
private fun NavigationScreenPreview() {
    NavigationScreen(rememberNavController())
}


@Composable
fun NavigationRoute(topNavHostController: NavHostController, viewModel: NavigationViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = viewModel, block = {

    })
    NavigationScreen(topNavHostController)
}

@Composable
fun NavigationScreen(topNavHostController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        MainBottomNavigation(navController)
    }) { it ->
        NavigationGraph(navController, Modifier.padding(it), topNavHostController)
    }
}


@Composable
private fun MainBottomNavigation(navHostController: NavHostController) {
    val screens = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Outlay,
        BottomNavigationScreens.Goal,
        BottomNavigationScreens.Profile
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        modifier = Modifier.padding(bottom = 8.dp, top = 8.dp, start = 15.dp, end = 15.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
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
    val isCurrent = currentDestination.isInCurrentGraph(screen.graph)
    val colorTint = if (isCurrent) null else ColorFilter.tint(White100)
    BottomNavigationItem(
        icon = {
            Image(
                painter = painterResource(id = screen.icon),
                contentDescription = null,
                colorFilter = colorTint
            )
        },
        selected = isCurrent,
        onClick = {
            navController.navigateSaveState(screen.graph)
        },
    )
}

