package com.example.api

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val token: Flow<String?>

    suspend fun isNeedOnBoarding(): Boolean

    suspend fun isNeedRegistration(): Boolean

    suspend fun prepareToken()
}