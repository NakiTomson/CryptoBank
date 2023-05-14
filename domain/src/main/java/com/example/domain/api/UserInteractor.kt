package com.example.domain.api

import com.example.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserInteractor {

    val token: Flow<String?>

    suspend fun isNeedOnBoarding(): Boolean

    suspend fun setNeedOnBoarding(isShow: Boolean)

    suspend fun isNeedRegistration(): Boolean

    suspend fun prepareToken()

    suspend fun saveUser(user: UserEntity)

    suspend fun getUser(): UserEntity?

    suspend fun clearUser()

}
