package com.example.tugas05_katalogbuku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CatalogUiState())
    val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

    private val _booksInternal = mutableListOf<Book>()

    init {
        loadInitialData()
    }

    // Fungsi bantuan untuk memuat data awal
    private fun loadInitialData() {
        _booksInternal.clear()
        _booksInternal.addAll(
            listOf(
                Book(1, "Laskar Pelangi", "Andrea Hirata", "Kisah inspiratif anak-anak Belitong."),
                Book(2, "Bumi Manusia", "Pramoedya Ananta Toer", "Kisah cinta Minke dan Annelies di era kolonial."),
                Book(3, "Negeri 5 Menara", "A. Fuadi", "Kisah santri dengan mantra Man Jadda Wajada."),
                Book(4, "Filosofi Kopi", "Dee Lestari", "Perjalanan mencari kopi terbaik di Indonesia."),
                Book(5, "Cantik Itu Luka", "Eka Kurniawan", "Epos sejarah berbalut realisme magis di Halimunda."),
                Book(6, "Gadis Kretek", "Ratih Kumala", "Sejarah rokok kretek dan pencarian cinta masa lalu."),
                Book(7, "Hujan", "Tere Liye", "Kisah persahabatan dan cinta di masa depan pasca bencana."),
                Book(8, "Aroma Karsa", "Dee Lestari", "Pencarian pusaka rahasia melalui indera penciuman.")
            )
        )
        // Matikan loading dan berikan data ke UI
        _uiState.update { it.copy(bookList = _booksInternal.toList(), isLoading = false) }
    }

    // EVENT 1: Tambah Buku
    fun addBook(title: String, author: String, description: String) {
        val newId = (_booksInternal.maxByOrNull { it.id }?.id ?: 0) + 1
        val newBook = Book(newId, title, author, description)

        _booksInternal.add(newBook)
        _uiState.update { it.copy(bookList = _booksInternal.toList()) }
    }

    fun getBookById(id: Int): Book? {
        return _booksInternal.find { it.id == id }
    }

    // EVENT 2: Hapus Semua Buku (Memicu kondisi Empty State / Non-Happy Path)
    fun clearBooks() {
        _booksInternal.clear()
        _uiState.update { it.copy(bookList = emptyList()) }
    }

    // EVENT 3: Refresh Data (Memicu kondisi Simulated Loading / Non-Happy Path)
    fun refreshBooks() {
        viewModelScope.launch {
            // 1. Nyalakan indikator loading di UI
            _uiState.update { it.copy(isLoading = true) }

            // 2. Tahan proses selama 1.5 detik (Simulasi jaringan lambat)
            delay(1500)

            // 3. Muat kembali data asli
            loadInitialData()
        }
    }
}