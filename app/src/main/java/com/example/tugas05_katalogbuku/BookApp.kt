package com.example.tugas05_katalogbuku //

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun BookApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        // 1. Rute Home
        composable(Screen.Home.route) {
            HomeScreen(
                books = BookRepository.books, // Membaca langsung dari Repositori Global
                onBookClick = { bookId ->
                    navController.navigate(Screen.Detail.createRoute(bookId))
                },
                onAddClick = {
                    navController.navigate(Screen.Form.route)
                }
            )
        }

        // 2. Rute Detail
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: -1

            // Mencari buku menggunakan Repositori Global
            val selectedBook = BookRepository.getBookById(bookId)

            DetailScreen(
                book = selectedBook,
                onNavigateUp = { navController.popBackStack() }
            )
        }

        // 3. Rute Form Tambah Buku
        composable(Screen.Form.route) {
            FormScreen(
                onNavigateUp = { navController.popBackStack() },
                onSaveClick = { title, author, description ->
                    // Menyimpan buku secara permanen ke Repositori Global
                    BookRepository.addBook(title, author, description)

                    navController.popBackStack()
                }
            )
        }
    }
}