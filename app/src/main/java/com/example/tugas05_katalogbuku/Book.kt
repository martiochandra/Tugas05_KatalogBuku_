package com.example.tugas05_katalogbuku // Sesuaikan dengan package Anda!

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity mengubah data class ini menjadi tabel database bernama "books"
@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) // Auto-increment ID
    val id: Int = 0,
    val title: String,
    val author: String,
    val description: String,

    // Syarat wajib terpenuhi: >4 field (id, title, author, description)
)