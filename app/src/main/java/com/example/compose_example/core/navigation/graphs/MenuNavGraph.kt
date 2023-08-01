package com.example.compose_example.core.navigation.graphs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_example.core.navigation.GraphDestinations
import com.example.compose_example.features.menu.presentation.route.FavoriteRoute
import com.example.compose_example.features.menu.presentation.route.HomeRoute
import com.example.compose_example.features.menu.utils.BottomBarItem

@Composable

fun MenuNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = GraphDestinations.MENU_ROUTE,
        startDestination = BottomBarItem.Home.route
    ) {
        composable(route = BottomBarItem.Home.route) {
            HomeRoute()
        }
        composable(route = BottomBarItem.Account.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.clickable {},
                    text = "nameee",
                    fontSize = MaterialTheme.typography.h3.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        composable(route = BottomBarItem.Favorite.route) {
            FavoriteRoute()
        }
        composable(route = BottomBarItem.Settings.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.clickable {},
                    text = "nameeee",
                    fontSize = MaterialTheme.typography.h3.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}