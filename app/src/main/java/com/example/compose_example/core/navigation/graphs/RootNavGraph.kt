package com.example.compose_example.core.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_example.core.navigation.GraphDestinations
import com.example.compose_example.features.menu.presentation.route.MenuRoute
import com.example.compose_example.features.onboarding.presentation.route.OnboardingRoute
import com.example.compose_example.features.splash.presentation.SplashRoute


@Composable
fun RootNavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        route = GraphDestinations.ROOT_ROUTE,
        startDestination = GraphDestinations.Splash_ROUTE
    ) {
        composable(GraphDestinations.Splash_ROUTE) {
            SplashRoute(
                toOnboardingScreen = {
                    navController.navigate(GraphDestinations.ONBOARDING_ROUTE) {
                        popUpTo(GraphDestinations.Splash_ROUTE) { inclusive = true }
                    }
                },
            )
        }
        composable(GraphDestinations.ONBOARDING_ROUTE) {
            OnboardingRoute(
                toLoginScreen = {
                    navController.navigate(GraphDestinations.AUTHENTICATION_ROUTE) {
                        popUpTo(GraphDestinations.ONBOARDING_ROUTE) { inclusive = true }
                    }
                },
            )
        }

        authNavGraph(navController = navController)
        composable(route = GraphDestinations.MENU_ROUTE) {
            MenuRoute()
        }
    }
}
