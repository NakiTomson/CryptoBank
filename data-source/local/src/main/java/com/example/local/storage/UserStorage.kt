package com.example.local.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.local.core.extension.appDataStore
import com.example.local.core.extension.get
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UserStorage @Inject constructor(private val context: Context) {

    private val userTokenKey = stringPreferencesKey("user_token")

    suspend fun getUserToken() = context.appDataStore
        .get(userTokenKey)

    suspend fun getUserTokenFlow() = context.appDataStore.data
        .map { it[userTokenKey] }

    suspend fun setUserToken(value: String) = context.appDataStore
        .edit { it[userTokenKey] = value }
}