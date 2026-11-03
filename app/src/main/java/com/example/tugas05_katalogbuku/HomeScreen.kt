package com.example.tugas05_katalogbuku // Sesuaikan dengan package Anda!

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
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
    onClearClick: () -> Unit,   // Callback baru
    onRefreshClick: () -> Unit, // Callback baru
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Katalog Buku", fontWeight = FontWeight.Bold) },
                actions = {
                    // Tombol Event Refresh
                    IconButton(onClick = onRefreshClick) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh Data")
                    }
                    // Tombol Event Clear
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
                Icon(Icons.Default.Add, contentDescription = "Tambah Buku Baru")
            }
        }
    ) { innerPadding ->

        // Logika Render UI (Non-Happy Path vs Happy Path)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                // KONDISI 1: Loading
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                // KONDISI 2: Empty State (List Kosong)
                uiState.bookList.isEmpty() -> {
                    Text(
                        text = "Katalog kosong. Silakan tambah atau refresh buku.",
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                // KONDISI 3: Happy Path (Data Tersedia)
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 88.dp),
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
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
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