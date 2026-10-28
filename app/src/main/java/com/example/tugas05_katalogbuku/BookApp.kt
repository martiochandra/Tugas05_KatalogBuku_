package com.example.tugas05_katalogbuku

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BookApp() {
    // NavController diinisialisasi HANYA di sini
    val navController = rememberNavController()

    // NavHost adalah wadah yang mengatur rute mana yang sedang aktif
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route // Layar pertama yang dibuka
    ) {

        // 1. Rute Home / List Screen
        composable(Screen.Home.route) {
            HomeScreen(
                books = dummyBooks, // Mengambil data in-memory dari Book.kt
                onBookClick = { bookId ->
                    // TODO Hari 3: Navigasi ke layar Detail dengan membawa ID
                },
                onAddClick = {
                    // TODO Hari 4: Navigasi ke layar Form Tambah
                }
            )
        }

        // Rute Screen.Detail dan Screen.Form akan kita tambahkan di Hari ke-3 dan ke-4
    }
}