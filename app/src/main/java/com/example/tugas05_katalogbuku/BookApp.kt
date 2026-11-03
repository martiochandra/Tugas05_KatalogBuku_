package com.example.tugas05_katalogbuku //

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun BookApp(
    // Menginisialisasi ViewModel di tingkat NavHost
    viewModel: CatalogViewModel = viewModel()
) {
    val navController = rememberNavController()

    // SYARAT WAJIB: Compose mengonsumsi state dengan collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        // 1. Rute Home
        composable(Screen.Home.route) {
            HomeScreen(
                uiState = uiState, // Mengirim UiState secara utuh ke layar
                onBookClick = { bookId ->
                    navController.navigate(Screen.Detail.createRoute(bookId)) {
                        launchSingleTop = true
                    }
                },
                onAddClick = {
                    navController.navigate(Screen.Form.route) {
                        launchSingleTop = true
                    }
                },
                onClearClick = { viewModel.clearBooks() },
                onRefreshClick = { viewModel.refreshBooks() }
            )
        }

        // 2. Rute Detail
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: -1

            // Meminta data satu buku spesifik dari ViewModel
            val selectedBook = viewModel.getBookById(bookId)

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
                    // Mengirim event ke ViewModel untuk memproses penambahan data
                    viewModel.addBook(title, author, description)

                    navController.popBackStack()
                }
            )
        }
    }
}