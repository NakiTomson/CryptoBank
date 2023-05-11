package com.example.local.storage

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.local.core.extension.appDataStore
import com.example.local.core.extension.get
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ConfigStorage @Inject constructor(@ApplicationContext private val context: Context) {

    private val isOnBoardingKey = booleanPreferencesKey("needShowOnBoarding")

    private val isRegistrationKey = booleanPreferencesKey("needShowRegistration")

    suspend fun isNeedOnBoarding(): Boolean = context.appDataStore
        .get(isOnBoardingKey) ?: false

    suspend fun updateNeedOnBoarding(value: Boolean) = context.appDataStore
        .edit { it[isOnBoardingKey] = value }

    suspend fun isNeedRegistration(): Boolean = context.appDataStore
        .get(isRegistrationKey) ?: true

    suspend fun updateNeedRegistration(value: Boolean) = context.appDataStore
        .edit { it[isRegistrationKey] = value }
}