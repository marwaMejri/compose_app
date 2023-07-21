package com.example.compose_example.features.authentification.presentation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_example.features.authentification.presentation.AuthenticationViewModel
import com.example.compose_example.features.authentification.presentation.views.AuthenticationScreen


@Composable
fun AuthenticationRoute(
    navigateToForget: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val viewModel: AuthenticationViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()
    AuthenticationScreen(
        viewState = state,
        events = viewModel::handleAuthenticationEvent,
        navigateToForget = {
            navigateToForget()
        },
        navigateToHome = {
            navigateToHome()
        }
    )
}