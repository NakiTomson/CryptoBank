package com.example.impl

import com.example.api.UserRepository
import com.example.local.storage.TokenStorage
import com.example.errors.ServerError
import com.example.local.storage.ConfigStorage
import com.example.remote.request.SessionRequest
import com.example.remote.response.NetworkResult
import com.example.remote.service.AuthService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val tokenStorage: TokenStorage,
    private val configStorage: ConfigStorage,
) : UserRepository {

    override val token: Flow<String?> = tokenStorage.getUserTokenFlow()

    override suspend fun isNeedOnBoarding(): Boolean {
       return configStorage.isNeedOnBoarding()
    }

    override suspend fun setNeedOnBoarding(isShow: Boolean) {
//        configStorage.updateNeedOnBoarding(isShow)
    }

    override suspend fun isNeedRegistration(): Boolean {
       return configStorage.isNeedRegistration()
    }

    override suspend fun setNeedRegistration(isShow: Boolean) {
        configStorage.updateNeedRegistration(isShow)
    }

    override suspend fun prepareToken() {
        val request = SessionRequest(tokenStorage.getUserToken())
        when (val result = authService.getSessionToken(request)) {
            is NetworkResult.Response.Success ->
                tokenStorage.setUserToken(result.data?.access_token ?: throw ServerError.UndefinedError())

            is NetworkResult.Response.Error -> tokenStorage.setUserToken(request.deviceId)
            is NetworkResult.Exception -> throw ServerError.NetworkError()
        }
    }
}
