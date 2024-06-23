package com.movix.android.component.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.movix.android.home.HomePage

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.HOME
    ) {
        composable(NavigationRoute.HOME) {
            HomePage()
        }
    }
}
