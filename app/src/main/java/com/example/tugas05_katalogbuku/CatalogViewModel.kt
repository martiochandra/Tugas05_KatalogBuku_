package com.example.tugas05_katalogbuku // Sesuaikan dengan package Anda!

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val repository: BookRepository,
    private val userPrefs: UserPreferencesRepository // Tambahan DataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(CatalogUiState(isLoading = true))
    val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

    init {
        // Menggabungkan 3 aliran data (Buku dari Room, Mode Gelap, Urutan)
        viewModelScope.launch {
            combine(
                repository.getAllBooksStream(),
                userPrefs.isDarkMode,
                userPrefs.isSortByTitle
            ) { books, isDark, isSort ->
                // Menerapkan logika pengurutan jika preferensi aktif
                val finalBooks = if (isSort) books.sortedBy { it.title } else books

                CatalogUiState(
                    bookList = finalBooks,
                    isLoading = false,
                    isDarkMode = isDark,
                    isSortByTitle = isSort
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    // Fungsi Toggle Preferensi Mode Gelap
    fun toggleDarkMode(current: Boolean) {
        viewModelScope.launch { userPrefs.saveDarkModePreference(!current) }
    }

    // Fungsi Toggle Preferensi Urutan
    fun toggleSortOrder(current: Boolean) {
        viewModelScope.launch { userPrefs.saveSortPreference(!current) }
    }

    fun addBook(title: String, author: String, description: String) {
        viewModelScope.launch { repository.insertBook(Book(title = title, author = author, description = description)) }
    }

    fun clearBooks() {
        viewModelScope.launch { repository.deleteAllBooks() }
    }

    fun refreshBooks() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            delay(1000)
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    // FACTORY yang diperbarui untuk menerima 2 Repository
    class Factory(
        private val repository: BookRepository,
        private val userPrefs: UserPreferencesRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CatalogViewModel(repository, userPrefs) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}