package com.example.domain.api

import kotlinx.coroutines.flow.Flow

interface UserInteractor {

    val token: Flow<String?>

    suspend fun isNeedOnBoarding(): Boolean

    suspend fun setNeedOnBoarding(isShow: Boolean)

    suspend fun isNeedRegistration(): Boolean

    suspend fun setNeedRegistration(isShow: Boolean)

    suspend fun prepareToken()

}
