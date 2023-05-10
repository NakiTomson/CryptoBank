package com.example.domain.impl

import com.example.domain.api.UserInteractor
import javax.inject.Inject

class UserInteractorImpl @Inject constructor() : UserInteractor {

    override suspend fun isOnBoarded(): Boolean {
        return false
    }

    override suspend fun setOnBoarded(isOnBoarded: Boolean) {

    }
}