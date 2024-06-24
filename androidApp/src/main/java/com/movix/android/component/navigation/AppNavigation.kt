package com.movix.android.component.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.movix.android.detail.DetailPage
import com.movix.android.main.screen.MainPage

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.MAIN.route
    ) {
        composable(
            route = NavigationRoute.MAIN.route,
            arguments = emptyList()
        ) {
            MainPage(
                onNavigateToDetail = { id ->
                    navController.navigate(
                        NavigationRoute.DETAIL.routeWith(id)
                    )
                }
            )
        }

        composable(
            route = NavigationRoute.DETAIL.route,
            arguments = listOf(
                navArgument(NavigationRoute.DETAIL.ID) {
                    type = NavType.LongType
                }
            )
        ) { navEntry ->
            val id = navEntry.arguments?.getLong(NavigationRoute.DETAIL.ID)
            id?.let {
                DetailPage(it)
            }
        }
    }
}
