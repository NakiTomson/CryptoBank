package com.example.api

import com.example.entity.OnBoardingEntity
import com.example.remote.response.OnBoardingResponse
import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {

    suspend fun getOnBoardingScreens(): List<OnBoardingEntity>

}