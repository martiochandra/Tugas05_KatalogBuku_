# Katalog Buku (Tugas 5 - Multi-Screen Navigation)

**Identitas Mahasiswa:**
*   **Nama:** MARTIO CHANDRA
*   **NIM:** 0102524723
*   **Kelas / Program Studi:** Informatika UAI
*   **Mata Kuliah:** Pemrograman Bergerak
*   **Dosen:** Dody

## Deskripsi Aplikasi
Aplikasi ini adalah implementasi *Multi-Screen Navigation* menggunakan Jetpack Compose. Sesuai dengan digit terakhir NIM (2), tema aplikasi yang dibuat adalah **Katalog Buku**. Aplikasi ini menggunakan *in-memory data* dan teknik *State Management* dengan pola *Single Source of Truth* (Singleton Repository) agar sinkronisasi data antar-layar berjalan mulus tanpa *state reset*.

## Fitur Utama & Navigation Graph
Aplikasi ini memiliki 3 tujuan (*destinations*) utama:

1.  **`home` (Layar Utama):**
    *   Menggunakan `LazyColumn` untuk menampilkan daftar buku.
    *   Terdapat tombol *Floating Action Button* (+) untuk menambah buku.
2.  **`detail/{bookId}` (Layar Detail):**
    *   Menerapkan *type-safe route* dengan argumen ID (`IntType`).
    *   Menangani kondisi *Invalid ID* (menampilkan pesan error jika buku tidak ditemukan).
    *   Hanya menerima data buku dan *callback* navigasi (*Stateless* terhadap NavController).
3.  **`form` (Layar Tambah Buku):**
    *   Memiliki 3 input teks (Judul, Penulis, Sinopsis).
    *   Memiliki validasi *real-time* (tombol "Simpan" hanya aktif jika Judul dan Penulis terisi).
    *   Menyimpan data langsung ke Singleton Repository agar layar `home` otomatis ter-*refresh*.

## Cara Menjalankan Project
1. *Clone* repositori ini melalui Android Studio (File > New > Project from Version Control).
2. Tunggu proses *Gradle Sync* selesai.
3. Jalankan (*Run*) aplikasi pada emulator atau perangkat Android fisik.
4. (*Known Limitation*): Data bersifat sementara (*in-memory*). Jika aplikasi ditutup paksa (*force close*) dari memori HP, daftar buku akan kembali ke 8 data *dummy* awal.

## Deklarasi Penggunaan AI
☑ Saya menggunakan AI generatif sebagai alat bantu.
*   **Tools:** Gemini AI
*   **Bagian yang dibantu AI:** Merancang arsitektur navigasi `NavHost`, memperbaiki *bug state reset* dengan pendekatan Singleton Repository, serta menuntun proses pembagian 5 *commit* progresif.