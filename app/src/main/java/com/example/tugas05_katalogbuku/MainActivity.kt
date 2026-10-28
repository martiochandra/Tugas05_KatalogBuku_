package com.example.tugas05_katalogbuku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
//Karena pengaturan rute (NavHost) sudah
// dipindah ke BookApp.kt, MainActivity kita sekarang menjadi sangat bersih.
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
import com.example.tugas05_katalogbuku.ui.theme.Tugas05_KatalogBukuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tugas05_KatalogBukuTheme {
                //Memanggil pengatur navigasi utama
                BookApp()
            }
        }
    }
}
