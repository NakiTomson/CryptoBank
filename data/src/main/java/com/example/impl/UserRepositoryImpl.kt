package com.example.impl

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.api.UserRepository
import com.example.local.core.extension.appDataStore
import com.example.local.storage.ConfigStorage
import com.example.remote.errors.ServerError
import com.example.remote.request.SessionRequest
import com.example.remote.response.NetworkResult
import com.example.remote.service.AuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val configStorage: ConfigStorage
) : UserRepository {

    override val token: Flow<String?> = configStorage.getUserTokenFlow()

    override suspend fun prepareToken() {
        val request = SessionRequest(configStorage.getUserToken())
        when (val result = authService.getSessionToken(request)) {
            is NetworkResult.Response.Success -> {
                val response = result.data ?: throw ServerError.UndefinedError()
                configStorage.setUserToken(response.access_token)
            }
            is NetworkResult.Response.Error -> {
                configStorage.setUserToken(request.deviceId)
            }

            is NetworkResult.Exception -> throw ServerError.NetworkError()
        }
    }
}
