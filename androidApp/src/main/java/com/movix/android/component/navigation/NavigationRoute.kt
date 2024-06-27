package com.movix.android.component.navigation

sealed class NavigationRoute(
    val route: String
) {

    data object Main : NavigationRoute("main")

    data object MovieDetail : NavigationRoute("movieDetail/{id}") {

        private const val BASE_ROUTE = "movieDetail"

        const val ID = "id"

        fun routeWith(id: Long): String = "$BASE_ROUTE/$id"

    }

}