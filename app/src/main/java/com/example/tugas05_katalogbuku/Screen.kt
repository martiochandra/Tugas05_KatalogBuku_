package com.example.tugas05_katalogbuku

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Form : Screen("form")

    // Rute detail membutuhkan argument ID
    object Detail : Screen("detail/{bookId}") {
        fun createRoute(bookId: Int) = "detail/$bookId"
    }
}
