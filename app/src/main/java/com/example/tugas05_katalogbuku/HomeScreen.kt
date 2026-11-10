package com.example.tugas05_katalogbuku // Sesuaikan dengan package Anda!

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: CatalogUiState,
    onBookClick: (Int) -> Unit,
    onAddClick: () -> Unit,
    onClearClick: () -> Unit,
    onRefreshClick: () -> Unit,
    // Dua fungsi tambahan (Kita gunakan langsung dari parameter, namun kita ambil dari parent jika perlu)
    // Untuk mempersingkat, kita akan panggil viewModel dari parameter atau tambahkan callback
    // (Biar tidak merombak NavHost lagi, kita tambahkan opsional callback)
    onToggleDarkClick: () -> Unit = {},
    onToggleSortClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Menerapkan MaterialTheme dinamis berdasarkan preferensi DataStore
    MaterialTheme(
        colorScheme = if (uiState.isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Katalog", fontWeight = FontWeight.Bold) },
                    actions = {
                        // Tombol Toggle Urutan (Sort)
                        IconButton(onClick = onToggleSortClick) {
                            Icon(Icons.Default.List, contentDescription = "Urutkan Abjad")
                        }
                        // Tombol Toggle Mode Gelap
                        IconButton(onClick = onToggleDarkClick) {
                            Icon(Icons.Default.Settings, contentDescription = "Mode Gelap")
                        }
                        IconButton(onClick = onClearClick) {
                            Icon(Icons.Default.Delete, contentDescription = "Hapus Semua")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = onAddClick) {
                    Icon(Icons.Default.Add, contentDescription = "Tambah")
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                if (uiState.bookList.isEmpty()) {
                    Text("Katalog kosong.", modifier = Modifier.align(Alignment.Center))
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(uiState.bookList) { book ->
                            BookListItem(book = book, onClick = { onBookClick(book.id) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookListItem(book: Book, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = book.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Karya: ${book.author}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}