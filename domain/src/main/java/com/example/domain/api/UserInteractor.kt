package com.example.domain.api

import kotlinx.coroutines.flow.Flow

interface UserInteractor {

    var isNeedShowOnBoarding: Boolean

    val token: Flow<String?>

    suspend fun prepareToken()

}