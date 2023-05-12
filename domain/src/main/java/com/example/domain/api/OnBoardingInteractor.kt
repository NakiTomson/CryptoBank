package com.example.domain.api

import com.example.entity.OnBoardingEntity

interface OnBoardingInteractor {

    suspend fun getOnBoardingScreens(): List<OnBoardingEntity>
}