package com.example.tugas05_katalogbuku

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    onNavigateUp: () -> Unit, // Callback untuk tombol Batal/Back
    onSaveClick: (String, String, String) -> Unit, // Callback untuk mengirim data ke NavHost
    modifier: Modifier = Modifier
) {
    // State untuk menyimpan teks yang diketik (dipertahankan saat rotasi layar)
    var title by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    // Logika Validasi: Judul dan Penulis tidak boleh kosong
    val isFormValid = title.isNotBlank() && author.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Buku Baru") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Batal")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Judul Buku") },
                modifier = Modifier.fillMaxWidth(),
                isError = title.isBlank() // Garis merah jika kosong
            )

            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Nama Penulis") },
                modifier = Modifier.fillMaxWidth(),
                isError = author.isBlank() // Garis merah jika kosong
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Sinopsis Singkat") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Tombol Batal
                OutlinedButton(
                    onClick = onNavigateUp,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Batal")
                }

                // Tombol Simpan
                Button(
                    onClick = { onSaveClick(title, author, description) },
                    modifier = Modifier.weight(1f),
                    enabled = isFormValid // Validasi: Tombol menyala jika data valid
                ) {
                    Text("Simpan")
                }
            }
        }
    }
}