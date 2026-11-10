package com.example.tugas05_katalogbuku

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Inisialisasi DataStore (Hanya boleh dibuat satu kali)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {

    private companion object {
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        val SORT_BY_TITLE = booleanPreferencesKey("sort_by_title")
    }

    // Membaca Preferensi 1: Mode Gelap
    val isDarkMode: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_DARK_MODE] ?: false
    }

    // Membaca Preferensi 2: Urutkan Abjad
    val isSortByTitle: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[SORT_BY_TITLE] ?: false
    }

    // Menyimpan Preferensi 1
    suspend fun saveDarkModePreference(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = isDarkMode
        }
    }

    // Menyimpan Preferensi 2
    suspend fun saveSortPreference(isSortByTitle: Boolean) {
        dataStore.edit { preferences ->
            preferences[SORT_BY_TITLE] = isSortByTitle
        }
    }
}