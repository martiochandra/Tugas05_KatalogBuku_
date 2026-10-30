package com.example.tugas05_katalogbuku // Sesuaikan dengan package Anda!

import androidx.compose.runtime.mutableStateListOf

// Model data buku
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String
)

// Menggunakan Singleton Object sebagai "Database Sementara"
object BookRepository {
    // mutableStateListOf memastikan UI selalu refresh saat ada perubahan
    val books = mutableStateListOf(
        Book(1, "Laskar Pelangi", "Andrea Hirata", "Kisah inspiratif anak-anak Belitong."),
        Book(2, "Bumi Manusia", "Pramoedya Ananta Toer", "Kisah cinta Minke dan Annelies di era kolonial."),
        Book(3, "Negeri 5 Menara", "A. Fuadi", "Kisah santri dengan mantra Man Jadda Wajada."),
        Book(4, "Filosofi Kopi", "Dee Lestari", "Perjalanan mencari kopi terbaik di Indonesia."),
        Book(5, "Cantik Itu Luka", "Eka Kurniawan", "Epos sejarah berbalut realisme magis di Halimunda."),
        Book(6, "Gadis Kretek", "Ratih Kumala", "Sejarah rokok kretek dan pencarian cinta masa lalu."),
        Book(7, "Hujan", "Tere Liye", "Kisah persahabatan dan cinta di masa depan pasca bencana."),
        Book(8, "Aroma Karsa", "Dee Lestari", "Pencarian pusaka rahasia melalui indera penciuman.")
    )

    // Fungsi untuk menambah buku baru
    fun addBook(title: String, author: String, description: String) {
        val newId = (books.maxByOrNull { it.id }?.id ?: 0) + 1
        books.add(Book(newId, title, author, description))
    }

    // Fungsi untuk mencari buku berdasarkan ID
    fun getBookById(id: Int): Book? {
        return books.find { it.id == id }
    }
}