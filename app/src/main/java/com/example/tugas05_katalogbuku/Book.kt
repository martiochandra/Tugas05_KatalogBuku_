package com.example.tugas05_katalogbuku // Sesuaikan dengan package Anda!

// Model data buku HANYA menyimpan struktur data (bersih dari state)
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String
)