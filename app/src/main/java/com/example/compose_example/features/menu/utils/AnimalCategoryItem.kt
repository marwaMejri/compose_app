package com.example.compose_example.features.menu.utils

import com.example.compose_example.R
import com.example.compose_example.features.menu.data.home.AnimalCategory

sealed class AnimalCategoryItem(
    val type: AnimalCategory,
    val title: Int,
    val image: Int,
) {
    object Dog : AnimalCategoryItem(
        type = AnimalCategory.DOG,
        title = R.string.dog_text,
        image = R.drawable.dog_category,
    )

    object Cat : AnimalCategoryItem(
        type = AnimalCategory.CAT,
        title = R.string.cat_text,
        image = R.drawable.cat_category,
    )

    object Fish : AnimalCategoryItem(
        type = AnimalCategory.FISH,
        title = R.string.fish_text,
        image = R.drawable.fish_category,
    )

    object Chick : AnimalCategoryItem(
        type = AnimalCategory.CHICK,
        title = R.string.chick_text,
        image = R.drawable.chick_category,
    )
}