package com.example.api

import com.example.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val token: Flow<String?>

    suspend fun isNeedOnBoarding(): Boolean

    suspend fun setNeedOnBoarding(isNeed: Boolean)

    suspend fun isNeedRegistration(): Boolean

    suspend fun setNeedRegistration(isNeed: Boolean)

    suspend fun prepareToken()

    suspend fun saveUser(user: UserEntity)

    suspend fun getUser():UserEntity?

    suspend fun clearUser()

}