package com.example.compose_example.features.authentification.presentation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_example.features.authentification.presentation.AuthenticationViewModel
import com.example.compose_example.features.authentification.presentation.views.VerificationScreen

@Composable
fun VerificationRoute(
    navigateToReset: () -> Unit,
    onBackClicked: () -> Unit,
) {
    val viewModel: AuthenticationViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()
    VerificationScreen(
        viewState = state,
        navigateToReset = { navigateToReset() },
        onBackClicked = { onBackClicked() },
        events = viewModel::handleAuthenticationEvent,
    )
}