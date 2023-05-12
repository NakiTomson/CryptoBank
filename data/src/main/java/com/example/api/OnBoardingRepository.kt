package com.example.api

import com.example.entity.OnBoardingEntity
import com.example.remote.response.OnBoardingResponse
import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {

    val onBoardings: Flow<List<OnBoardingEntity>>

    suspend fun getOnBoardingScreens()

}