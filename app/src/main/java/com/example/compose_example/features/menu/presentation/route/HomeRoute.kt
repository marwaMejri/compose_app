package com.example.compose_example.features.menu.presentation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_example.features.menu.presentation.HomeViewModel
import com.example.compose_example.features.menu.presentation.views.HomeScreen

@Composable
fun HomeRoute() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()
    HomeScreen(state,viewModel::handleHomeEvent)
}