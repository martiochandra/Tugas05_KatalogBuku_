package com.example.tugas05_katalogbuku

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Mendefinisikan entitas tabel dan versi database
@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    // Mendaftarkan DAO agar bisa diakses
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var Instance: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            // Memastikan hanya ada 1 instance database yang aktif di dalam memori
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    BookDatabase::class.java,
                    "katalog_buku_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}