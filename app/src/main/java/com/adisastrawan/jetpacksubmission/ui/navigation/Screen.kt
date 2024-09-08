package com.adisastrawan.jetpacksubmission.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{name}") {
        fun createRoute(name: String) = "detail/$name"
    }
    data object Favorite : Screen("favorite")
    data object About : Screen("about")
}