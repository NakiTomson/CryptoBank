package com.example.api

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val token: Flow<String?>

    suspend fun prepareToken()
}