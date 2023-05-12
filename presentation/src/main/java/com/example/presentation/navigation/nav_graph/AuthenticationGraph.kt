package com.example.presentation.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.presentation.navigation.sreens.AuthenticationScreens
import com.example.presentation.navigation.sreens.TopScreens
import com.example.presentation.ui.authentication.forget.screen.ForgetScreen
import com.example.presentation.ui.authentication.log_in.screen.SignInScreen
import com.example.presentation.ui.authentication.sign_up.screen.SignUpScreen


fun NavGraphBuilder.authenticationNavGraph(
    navController: NavHostController
) {
    navigation(startDestination = AuthenticationScreens.SingIn.route, route = AuthenticationScreens.getGraph()) {
        composable(route = AuthenticationScreens.SingIn.route) {
            SignInScreen() {
                navController.navigate(AuthenticationScreens.SingUp.route)
            }
        }
        composable(route = AuthenticationScreens.SingUp.route) {
            SignUpScreen() {
                navController.navigate(AuthenticationScreens.Forget.route)
            }
        }
        composable(route = AuthenticationScreens.Forget.route) {
            ForgetScreen() {
                navController.navigate(TopScreens.Navigation.route)
            }
        }
    }
}