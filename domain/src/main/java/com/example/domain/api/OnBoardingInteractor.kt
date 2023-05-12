package com.example.domain.api

import com.example.entity.OnBoardingEntity
import kotlinx.coroutines.flow.Flow

interface OnBoardingInteractor {

    val onBoardings: Flow<List<OnBoardingEntity>>

    suspend fun getOnBoardingScreens()
}