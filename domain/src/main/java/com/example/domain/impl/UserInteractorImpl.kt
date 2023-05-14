package com.example.domain.impl

import com.example.api.UserRepository
import com.example.core.Dispatchers
import com.example.domain.api.UserInteractor
import com.example.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserInteractorImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatchers: Dispatchers,
) : UserInteractor {

    override val token: Flow<String?> = userRepository.token

    override suspend fun isNeedOnBoarding(): Boolean = withContext(dispatchers.io) {
        userRepository.isNeedOnBoarding()
    }

    override suspend fun setNeedOnBoarding(isShow: Boolean) = withContext(dispatchers.io) {
        userRepository.setNeedOnBoarding(isShow)
    }

    override suspend fun isNeedRegistration(): Boolean = withContext(dispatchers.io) {
        userRepository.isNeedRegistration()
    }

    override suspend fun prepareToken(): Unit = withContext(dispatchers.io) {
        userRepository.prepareToken()
    }

    override suspend fun saveUser(user: UserEntity): Unit = withContext(dispatchers.io) {
        userRepository.saveUser(user)
    }

    override suspend fun getUser(): UserEntity? = withContext(dispatchers.io) {
        userRepository.getUser()
    }

    override suspend fun clearUser() = withContext(dispatchers.io) {
        userRepository.clearUser()
    }

}
