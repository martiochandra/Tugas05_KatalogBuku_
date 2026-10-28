package com.example.tugas05_katalogbuku

// Model data buku
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String
)

// In-memory data (minimal 8 item sesuai syarat tugas)
val dummyBooks = mutableListOf(
    Book(1, "Laskar Pelangi", "Andrea Hirata", "Kisah inspiratif anak-anak Belitong."),
    Book(2, "Bumi Manusia", "Pramoedya Ananta Toer", "Kisah cinta Minke dan Annelies di era kolonial."),
    Book(3, "Negeri 5 Menara", "A. Fuadi", "Kisah santri dengan mantra Man Jadda Wajada."),
    Book(4, "Filosofi Kopi", "Dee Lestari", "Perjalanan mencari kopi terbaik di Indonesia."),
    Book(5, "Cantik Itu Luka", "Eka Kurniawan", "Epos sejarah berbalut realisme magis di Halimunda."),
    Book(6, "Gadis Kretek", "Ratih Kumala", "Sejarah rokok kretek dan pencarian cinta masa lalu."),
    Book(7, "Hujan", "Tere Liye", "Kisah persahabatan dan cinta di masa depan pasca bencana."),
    Book(8, "Aroma Karsa", "Dee Lestari", "Pencarian pusaka rahasia melalui indera penciuman.")
)
