package com.example.compose_example.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_example.core.navigation.Destinations.ONBOARDING_ROUTE
import com.example.compose_example.core.navigation.Destinations.Splash_ROUTE
import com.example.compose_example.features.onboarding.presentation.route.OnboardingRoute
import com.example.compose_example.features.splash.presentation.SplashRoute


@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Splash_ROUTE,
    ) {
        composable(Splash_ROUTE) {
            SplashRoute(
                toOnboardingScreen = {
                    navController.navigate(ONBOARDING_ROUTE)
                },
            )
        }
        composable(ONBOARDING_ROUTE) {
            OnboardingRoute(
                toLoginScreen = {
                    navController.navigate(Destinations.LOGIN_ROUTE)
                },
            )
        }
    }
}