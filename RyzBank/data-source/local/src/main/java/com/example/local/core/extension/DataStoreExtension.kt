package com.example.local.core.extension

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

suspend fun <T> DataStore<Preferences>.get(
    key: Preferences.Key<T>,
    defaultValue: T? = null,
): T? {
    return this.data.map { it[key] }
        .firstOrNull() ?: defaultValue
}
