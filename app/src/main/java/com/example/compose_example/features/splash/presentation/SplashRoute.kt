package com.example.compose_example.features.splash.presentation

import androidx.compose.runtime.Composable

@Composable
fun SplashRoute(
    toOnboardingScreen: () -> Unit,
) {
    SplashScreen(
        toOnboardingScreen = toOnboardingScreen
    )
}
