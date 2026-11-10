package com.example.tugas05_katalogbuku

// Data class ini adalah "Foto Polaroird" dari kondisi layar saat ini (Immutable)
data class CatalogUiState(
    val bookList: List<Book> = emptyList(), // Daftar buku yang tampil
    val isLoading: Boolean = false,         // Simulasi loading
    val errorMessage: String? = null,        // Menyimpan pesan error jika ada
    // Tambahan 2 preferensi untuk DataStore (Syarat Wajib)
    val isDarkMode: Boolean = false,
    val isSortByTitle: Boolean = false
)