package com.movix.android.component.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.movix.android.detail.screen.MovieDetailPage
import com.movix.android.main.screen.MainPage

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Main.route
    ) {
        composable(
            route = NavigationRoute.Main.route,
            arguments = emptyList()
        ) {
            MainPage(
                onNavigateToDetail = { id ->
                    navController.navigate(
                        NavigationRoute.MovieDetail.routeWith(id)
                    )
                }
            )
        }

        composable(
            route = NavigationRoute.MovieDetail.route,
            arguments = listOf(
                navArgument(NavigationRoute.MovieDetail.ID) {
                    type = NavType.LongType
                }
            )
        ) { navEntry ->
            val id = navEntry.arguments?.getLong(NavigationRoute.MovieDetail.ID)
            id?.also {
                MovieDetailPage(it)
            }
        }
    }
}
