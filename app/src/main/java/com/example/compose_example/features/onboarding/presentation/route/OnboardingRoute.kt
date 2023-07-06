package com.example.compose_example.features.onboarding.presentation.route

import androidx.compose.runtime.Composable
import com.example.compose_example.features.onboarding.presentation.OnboardingScreen

@Composable
fun OnboardingRoute(
    toLoginScreen: () -> Unit,
) {
    OnboardingScreen(
        toLoginScreen = toLoginScreen
    )
}
