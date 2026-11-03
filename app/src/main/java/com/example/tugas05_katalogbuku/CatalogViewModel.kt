package com.example.tugas05_katalogbuku

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CatalogViewModel : ViewModel() {

    // SYARAT WAJIB: MutableStateFlow harus PRIVATE (hanya ViewModel yang boleh mengubah isinya)
    private val _uiState = MutableStateFlow(CatalogUiState())

    // SYARAT WAJIB: Ekspos read-only StateFlow ke UI (Layar hanya boleh membaca)
    val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

    // Memasukkan 8 data dummy awal ke dalam list internal ViewModel
    private val _booksInternal = mutableListOf(
        Book(1, "Laskar Pelangi", "Andrea Hirata", "Kisah inspiratif anak-anak Belitong."),
        Book(2, "Bumi Manusia", "Pramoedya Ananta Toer", "Kisah cinta Minke dan Annelies di era kolonial."),
        Book(3, "Negeri 5 Menara", "A. Fuadi", "Kisah santri dengan mantra Man Jadda Wajada."),
        Book(4, "Filosofi Kopi", "Dee Lestari", "Perjalanan mencari kopi terbaik di Indonesia."),
        Book(5, "Cantik Itu Luka", "Eka Kurniawan", "Epos sejarah berbalut realisme magis di Halimunda."),
        Book(6, "Gadis Kretek", "Ratih Kumala", "Sejarah rokok kretek dan pencarian cinta masa lalu."),
        Book(7, "Hujan", "Tere Liye", "Kisah persahabatan dan cinta di masa depan pasca bencana."),
        Book(8, "Aroma Karsa", "Dee Lestari", "Pencarian pusaka rahasia melalui indera penciuman.")
    )

    init {
        // Saat ViewModel pertama kali dibuat, masukkan data buku ke dalam UiState
        _uiState.update { currentState ->
            currentState.copy(bookList = _booksInternal.toList())
        }
    }

    // EVENT 1: Fungsi untuk menambah buku baru
    fun addBook(title: String, author: String, description: String) {
        val newId = (_booksInternal.maxByOrNull { it.id }?.id ?: 0) + 1
        val newBook = Book(newId, title, author, description)

        _booksInternal.add(newBook)

        // Perbarui "Foto Polaroid" (UiState) agar UI tahu ada buku baru
        _uiState.update { currentState ->
            currentState.copy(bookList = _booksInternal.toList())
        }
    }

    // Fungsi untuk mendapatkan spesifik 1 buku (untuk Detail Screen)
    fun getBookById(id: Int): Book? {
        return _booksInternal.find { it.id == id }
    }

    // TODO EVENT 2 & 3: Kita akan tambahkan fitur Hapus dan Reset di tahap selanjutnya
}