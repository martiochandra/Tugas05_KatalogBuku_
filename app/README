# Katalog Buku (Tugas 7 - Local Persistence: Room + DataStore)

**Identitas Mahasiswa:**
*   **Nama:** MARTIO CHANDRA
*   **NIM:** 0102524723

## Deskripsi Aplikasi
Aplikasi Katalog Buku ini telah direfaktor untuk mendukung penyimpanan data lokal (*Local Persistence*). Data utama (buku) kini disimpan ke dalam fisik perangkat menggunakan **Room Database**, sedangkan preferensi pengguna disimpan menggunakan **DataStore Preferences**. Arsitektur aplikasi menggunakan pola *Repository* untuk menjembatani Database dengan *ViewModel* secara *Unidirectional Data Flow* (UDF).

## Fitur Local Persistence
1.  **Room Database:**
    *   Menyimpan data buku (ID, Judul, Penulis, Sinopsis) ke dalam tabel SQLite.
    *   Mendukung operasi Insert, Get All (Flow), Get By ID (Flow), Delete, dan Delete All.
2.  **DataStore Preferences:**
    *   Menyimpan pengaturan *Dark Mode* (Mode Gelap).
    *   Menyimpan pengaturan pengurutan abjad (*Sort by Title*).

## Deklarasi Penggunaan AI
☑ Saya menggunakan AI generatif sebagai alat bantu.
*   **Tools:** Gemini AI
*   **Bagian yang dibantu AI:** Konfigurasi KAPT untuk Room, perancangan `BookRepository`, dan penggabungan aliran data (*Flow*) dari Room dan DataStore di dalam *ViewModel*.