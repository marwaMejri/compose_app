package com.example.compose_example.features.menu.utils

import com.example.compose_example.R


sealed class BottomBarItem(
    val route: String,
    val title: String,
    val activeIcon: Int,
    val inactiveIcon: Int
) {
    object Home : BottomBarItem(
        route = "HOME",
        title = "Home",
        activeIcon = R.drawable.ic_home_filled,
        inactiveIcon = R.drawable.ic_home
    )

    object Favorite : BottomBarItem(
        route = "ACCOUNT",
        title = "Account",
        activeIcon = R.drawable.ic_user_filled,
        inactiveIcon = R.drawable.ic_user,
    )

    object Account : BottomBarItem(
        route = "FAVORITE",
        title = "Favorites",
        activeIcon = R.drawable.ic_favorite_filled,
        inactiveIcon = R.drawable.ic_favorite,
    )

    object Settings : BottomBarItem(
        route = "SETTINGS",
        title = "Settings",
        inactiveIcon = R.drawable.ic_settings,
        activeIcon = R.drawable.ic_settings_filled,
    )
}