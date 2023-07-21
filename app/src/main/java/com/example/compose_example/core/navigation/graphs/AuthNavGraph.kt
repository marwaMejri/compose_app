package com.example.compose_example.core.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.compose_example.core.navigation.GraphDestinations
import com.example.compose_example.features.authentification.presentation.routes.AuthenticationRoute
import com.example.compose_example.features.authentification.presentation.routes.ForgetPasswordRoute
import com.example.compose_example.features.authentification.presentation.routes.ResetPasswordRoute
import com.example.compose_example.features.authentification.presentation.routes.VerificationRoute

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = GraphDestinations.AUTHENTICATION_ROUTE,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            AuthenticationRoute(
                navigateToForget = {
                    navController.navigate(AuthScreen.Forgot.route)
                },
                navigateToHome = {
                    navController.navigate(GraphDestinations.MENU_ROUTE)
                }
            )
        }
        composable(route = AuthScreen.Forgot.route) {
            ForgetPasswordRoute(
                onSendClicked = {
                    navController.navigate(AuthScreen.Otp.route)
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = AuthScreen.Otp.route) {
            VerificationRoute(
                navigateToReset = {
                    navController.navigate(AuthScreen.Reset.route)
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = AuthScreen.Reset.route) {
            ResetPasswordRoute(
                navigateToHome = {
                    navController.navigate(GraphDestinations.MENU_ROUTE) {
                        popUpTo(GraphDestinations.AUTHENTICATION_ROUTE) {
                            inclusive = true
                        }
                    }
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object Otp : AuthScreen(route = "OTP")
    object Forgot : AuthScreen(route = "FORGOT")
    object Reset : AuthScreen(route = "RESET")
}