package com.example.tugas05_katalogbuku //

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun BookApp() {
    val context = LocalContext.current

    // Inisialisasi Database dan 2 Repository
    val database = BookDatabase.getDatabase(context)
    val bookRepository = BookRepository(database.bookDao())
    val userPrefsRepository = UserPreferencesRepository(context.dataStore) // Tambahan

    val viewModel: CatalogViewModel = viewModel(
        factory = CatalogViewModel.Factory(bookRepository, userPrefsRepository) // Tambahan
    )

    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        // Rute Home
        composable(Screen.Home.route) {
            HomeScreen(
                uiState = uiState,
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
                onRefreshClick = { viewModel.refreshBooks() }, // <-- PASTIKAN ADA KOMA DI SINI
                onToggleDarkClick = { viewModel.toggleDarkMode(uiState.isDarkMode) },
                onToggleSortClick = { viewModel.toggleSortOrder(uiState.isSortByTitle) }
            )
        }

        // Rute Detail
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: -1

            // Mencari buku langsung dari state UI yang sudah tersinkronisasi dengan Room
            val selectedBook = uiState.bookList.find { it.id == bookId }

            DetailScreen(
                book = selectedBook,
                onNavigateUp = { navController.popBackStack() }
            )
        }

        // Rute Form Tambah Buku
        composable(Screen.Form.route) {
            FormScreen(
                onNavigateUp = { navController.popBackStack() },
                onSaveClick = { title, author, description ->
                    viewModel.addBook(title, author, description)
                    navController.popBackStack()
                }
            )
        }
    }
}