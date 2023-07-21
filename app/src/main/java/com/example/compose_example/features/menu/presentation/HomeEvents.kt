package com.example.compose_example.features.menu.presentation

import com.example.compose_example.features.menu.data.home.AnimalCategory


sealed class HomeEvents {
    data class ToggleAnimation(val value: Boolean) : HomeEvents()
    data class OnCategoryClicked(val value: Int, val category: AnimalCategory) : HomeEvents()
}