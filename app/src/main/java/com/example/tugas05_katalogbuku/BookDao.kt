package com.example.tugas05_katalogbuku

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    // 1. Read berbasis Flow (Syarat Wajib)
    @Query("SELECT * FROM books ORDER BY id DESC")
    fun getAllBooks(): Flow<List<Book>>

    // 2. Operasi Insert (Syarat Wajib)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    // 3. Operasi Get By ID (Syarat Tambahan Wajib)
    @Query("SELECT * FROM books WHERE id = :bookId")
    fun getBookById(bookId: Int): Flow<Book?>

    // 4. Operasi Delete (Syarat Tambahan Wajib)
    @Delete
    suspend fun deleteBook(book: Book)

    // 5. Operasi Delete All (Untuk fitur "Clear All" yang sudah kita buat di P6)
    @Query("DELETE FROM books")
    suspend fun deleteAllBooks()
}