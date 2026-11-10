package com.example.tugas05_katalogbuku

import kotlinx.coroutines.flow.Flow

// Repository bertugas sebagai jembatan tunggal antara ViewModel dan Database
class BookRepository(private val bookDao: BookDao) {

    // 1. Mengambil semua data buku dalam bentuk Flow
    // (Data akan mengalir terus secara real-time dari database ke UI)
    fun getAllBooksStream(): Flow<List<Book>> = bookDao.getAllBooks()

    // 2. Mengambil 1 buku spesifik berdasarkan ID dalam bentuk Flow
    fun getBookStream(id: Int): Flow<Book?> = bookDao.getBookById(id)

    // 3. Operasi Menambah Buku (Harus suspend karena berjalan di luar Main Thread)
    suspend fun insertBook(book: Book) = bookDao.insertBook(book)

    // 4. Operasi Menghapus 1 Buku
    suspend fun deleteBook(book: Book) = bookDao.deleteBook(book)

    // 5. Operasi Menghapus Semua Buku (Untuk fitur Clear)
    suspend fun deleteAllBooks() = bookDao.deleteAllBooks()
}