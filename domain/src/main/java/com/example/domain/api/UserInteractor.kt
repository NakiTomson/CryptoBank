package com.example.domain.api

interface UserInteractor {

    suspend fun isOnBoarded(): Boolean
    suspend fun setOnBoarded(isOnBoarded: Boolean)

}