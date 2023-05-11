package com.example.local.storage

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.local.core.extension.appDataStore
import com.example.local.core.extension.get
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ConfigStorage @Inject constructor(@ApplicationContext private val context: Context) {

    val userTokenKey = stringPreferencesKey("user_token")

    suspend fun getUserToken(): String = context.appDataStore
        .get(userTokenKey) ?: deviceId

    fun getUserTokenFlow() = context.appDataStore.data
        .map { it[userTokenKey] }

    suspend fun setUserToken(value: String) = context.appDataStore
        .edit { it[userTokenKey] = value }

    companion object {
        private const val deviceId = "1k882so2wxl"
    }
}
