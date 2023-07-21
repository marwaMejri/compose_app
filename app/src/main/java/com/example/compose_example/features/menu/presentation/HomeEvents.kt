package com.example.compose_example.features.menu.presentation

import com.example.compose_example.features.menu.data.home.AnimalCategory
import com.example.compose_example.features.menu.data.home.AnimalItem


sealed class HomeEvents {
    data class ToggleAnimation(val value: Boolean) : HomeEvents()
    data class OnCategoryClicked(val value: Int, val category: AnimalCategory) : HomeEvents()
    data class AddToFavorite(val item: AnimalItem) : HomeEvents()

}