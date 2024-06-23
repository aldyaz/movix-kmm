package com.movix.android.component.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.movix.android.main.screen.MainPage

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.MAIN
    ) {
        composable(NavigationRoute.MAIN) {
            MainPage()
        }
    }
}
