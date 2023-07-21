package com.example.compose_example.features.menu.data.home

data class AnimalItem(
    val name: String,
    val distance: Double,
    val image: Int,
    val isFavorite: Boolean,
//    val location: String,
//    val sex: Sex,
//    val age: Int,
//    val weight: Double,
//    val description: String,
//    val category: AnimalCategory,
//    val owner: Owner,
//    val size: AnimalSize,
)

data class Owner(
    val name: String,
    val location: String,
    val email: String,
    val phoneNumber: String,
    val image: Int,
)
