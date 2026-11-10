package com.example.tugas05_katalogbuku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogViewModel(private val repository: BookRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CatalogUiState(isLoading = true))
    val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

    init {
        // Membaca data dari Room Database secara Real-Time menggunakan Flow
        viewModelScope.launch {
            repository.getAllBooksStream().collect { books ->
                _uiState.update { it.copy(bookList = books, isLoading = false) }
            }
        }
    }

    // EVENT 1: Tambah Buku ke Database Room
    fun addBook(title: String, author: String, description: String) {
        viewModelScope.launch {
            // Kita tidak perlu memasukkan ID, karena autoGenerate = true di Room akan mengaturnya
            val newBook = Book(title = title, author = author, description = description)
            repository.insertBook(newBook)
        }
    }

    // EVENT 2: Hapus Semua Buku dari Database Room
    fun clearBooks() {
        viewModelScope.launch {
            repository.deleteAllBooks()
        }
    }

    // EVENT 3: Refresh (Simulasi Loading UI)
    fun refreshBooks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(1000) // Jeda 1 detik
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    // FACTORY: Diperlukan untuk menyuntikkan Repository ke dalam ViewModel
    class Factory(private val repository: BookRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CatalogViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}