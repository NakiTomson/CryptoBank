package com.example.domain.impl

import com.example.api.UserRepository
import com.example.core.Dispatchers
import com.example.domain.api.UserInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserInteractorImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatchers: Dispatchers,
) : UserInteractor {

    override var isNeedShowOnBoarding: Boolean
        get() = true
        set(value) {}

    override val token: Flow<String?> = userRepository.token

    override suspend fun prepareToken(): Unit = withContext(dispatchers.io) {
        userRepository.prepareToken()
    }

}