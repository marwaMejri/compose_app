package com.example.compose_example.features.menu.presentation

import androidx.lifecycle.ViewModel
import com.example.compose_example.features.menu.data.home.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()
    fun handleHomeEvent(event: HomeEvents) {
        _uiState.update { currentState ->
            when (event) {
                is HomeEvents.ToggleAnimation -> {
                    currentState.copy(initAnimation = event.value)
                }
                is HomeEvents.OnCategoryClicked -> {
                    currentState.copy(clickedCategoryIndex = event.value)
                }
            }
        }

    }
}