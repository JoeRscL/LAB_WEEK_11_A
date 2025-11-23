package com.example.lab_week_11_a

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Membuat DataStore
val Context.dataStore by preferencesDataStore(name = "settings_prefs")

class SettingsStore(private val context: Context) {

    // Key penyimpanan
    private val TEXT_KEY = stringPreferencesKey("text_key")

    // Flow untuk ambil text
    val text: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[TEXT_KEY] ?: ""
    }

    // Fungsi simpan text
    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[TEXT_KEY] = text
        }
    }
}