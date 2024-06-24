package com.movix.android.component.navigation

sealed class NavigationRoute(
    val route: String
) {

    data object MAIN : NavigationRoute("main")

    data object DETAIL : NavigationRoute("detail/{id}") {

        private const val BASE_ROUTE = "detail"

        const val ID = "id"

        fun routeWith(id: Long): String = "$BASE_ROUTE/$id"

    }

}