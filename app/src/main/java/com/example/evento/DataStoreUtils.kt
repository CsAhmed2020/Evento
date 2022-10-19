package com.example.evento

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.evento.app.MyApplication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object DataStoreUtils {
    fun readPreference(key: String, defaultValue: Boolean): Boolean {
        val keyPreferences = booleanPreferencesKey(key)
        return runBlocking {
            MyApplication.context.dataStore.data.map {
                it[keyPreferences] ?: defaultValue
            }.first()
        }
    }

    suspend fun savePreference(key: String, value: Boolean) {
        val keyPreferences = booleanPreferencesKey(key)
        MyApplication.context.dataStore.edit {
            it[keyPreferences] = value
        }
    }

}