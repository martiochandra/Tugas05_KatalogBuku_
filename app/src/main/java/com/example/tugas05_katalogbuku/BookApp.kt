package com.example.tugas05_katalogbuku

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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
                    // Menjalankan navigasi dengan ID yang dinamis (misal: "detail/1")
                    navController.navigate(Screen.Detail.createRoute(bookId))

                },
                onAddClick = {
                    // TODO Hari 4: Navigasi ke layar Form Tambah
                }
            )
        }

        // Rute Screen.Detail dan Screen.Form akan kita tambahkan di Hari ke-3 dan ke-4
        composable(
            route = Screen.Detail.route, // Menangkap rute "detail/{bookId}"
            arguments = listOf(navArgument("bookId") { type = NavType.IntType }) // Deklarasi tipe argumen
        ) { backStackEntry ->
            // Mengambil nilai ID dari URL/Route
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: -1

            // Mencari buku di database (in-memory) berdasarkan ID
            val selectedBook = dummyBooks.find { it.id == bookId }

            // Memanggil DetailScreen tanpa mengirimkan NavController (Stateless)
            DetailScreen(
                book = selectedBook,
                onNavigateUp = {
                    // popBackStack akan mencabut layar Detail dari tumpukan memori
                    // dan mengembalikan user ke layar sebelumnya (Home)
                    navController.popBackStack()
                }
            )
        }
    }
}