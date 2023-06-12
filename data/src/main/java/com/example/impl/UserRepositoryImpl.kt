package com.example.impl

import com.example.api.UserRepository
import com.example.entity.AuthorizationType
import com.example.entity.AuthorizationType.Companion.getEnumValue
import com.example.entity.UserEntity
import com.example.local.storage.TokenStorage
import com.example.errors.ServerError
import com.example.local.entity.UserDb
import com.example.local.storage.ConfigStorage
import com.example.local.storage.UserRoomStorage
import com.example.remote.request.SessionRequest
import com.example.remote.response.NetworkResult
import com.example.remote.service.AuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val tokenStorage: TokenStorage,
    private val configStorage: ConfigStorage,
    private val userRoomStorage: UserRoomStorage,
) : UserRepository {

    override val token: Flow<String?> = tokenStorage.getUserTokenFlow()

    override suspend fun isNeedOnBoarding(): Boolean {
        return configStorage.isNeedOnBoarding()
    }

    override suspend fun setNeedOnBoarding(isNeed: Boolean) {
        configStorage.updateNeedOnBoarding(isNeed)
    }

    override suspend fun isNeedRegistration(): Boolean {
        return configStorage.isNeedRegistration()
    }

    override suspend fun setNeedRegistration(isNeed: Boolean) {
        configStorage.updateNeedRegistration(isNeed)
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

    override suspend fun saveUser(user: UserEntity) {
        userRoomStorage.saveUser(
            UserDb(
                user.id, user.email, user.type.type,
                user.name ?: "Dmitry",
                user.avatar ?: "https://i.ibb.co/vP5hj57/sample-icon.jpg"
            )
        )
    }

    override suspend fun getUser(): UserEntity? {
        val user = userRoomStorage.getUser() ?: return null
        return UserEntity(
            user.id,
            user.email,
            getEnumValue(user.type),
            user.name,
            user.avatar,
        )
    }

    override suspend fun clearUser() {
        userRoomStorage.clearUser()
    }
}
