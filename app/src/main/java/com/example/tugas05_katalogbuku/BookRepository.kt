package com.example.tugas05_katalogbuku // Sesuaikan dengan package Anda!

import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {

    fun getAllBooksStream(): Flow<List<Book>> = bookDao.getAllBooks()

    fun getBookStream(id: Int): Flow<Book?> = bookDao.getBookById(id)

    suspend fun insertBook(book: Book) {
        bookDao.insertBook(book)
    }

    suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }

    suspend fun deleteAllBooks() {
        bookDao.deleteAllBooks()
    }
}