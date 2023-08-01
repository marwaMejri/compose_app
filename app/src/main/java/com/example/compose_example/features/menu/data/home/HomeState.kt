package com.example.compose_example.features.menu.data.home

data class HomeState(
    val initAnimation: Boolean = false,
    val clickedCategoryIndex: Int = 0,
    val favoriteList: ArrayList<AnimalItem> = arrayListOf(),
    val animalList: ArrayList<AnimalItem> = arrayListOf(),
    val filteredAnimalList: ArrayList<AnimalItem> = arrayListOf(),
    val scrollToFirstItem: Boolean = false,
    val launchFavoriteAnimation: Boolean = false,
    val favoriteAnimationItemIndex: Int = -1,
    val launchRemoveFavoriteAnimation: Boolean = false,
)