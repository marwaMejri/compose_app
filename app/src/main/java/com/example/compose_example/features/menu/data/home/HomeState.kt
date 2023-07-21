package com.example.compose_example.features.menu.data.home

data class HomeState(
    val initAnimation: Boolean = false,
    val clickedCategoryIndex: Int = 0,
)