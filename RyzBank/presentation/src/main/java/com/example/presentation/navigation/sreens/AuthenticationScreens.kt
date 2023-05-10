package com.example.presentation.navigation.sreens


sealed class AuthenticationScreens(val route: String) {
    object SingIn : AuthenticationScreens(SingInRoute)
    object SingUp : AuthenticationScreens(SingUpRoute)
    object Forget : AuthenticationScreens(ForgetRoute)

    companion object {
        private const val SingInRoute = "sing_in_screen"
        private const val SingUpRoute = "sing_up_screen"
        private const val ForgetRoute = "forget_screen"

        private const val AuthenticationGraph = "authentication_graph"
        fun getGraph(): String = AuthenticationGraph
    }
}