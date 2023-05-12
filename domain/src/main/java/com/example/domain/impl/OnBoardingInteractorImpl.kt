package com.example.domain.impl

import com.example.api.OnBoardingRepository
import com.example.core.Dispatchers
import com.example.domain.api.OnBoardingInteractor
import com.example.entity.OnBoardingEntity
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnBoardingInteractorImpl @Inject constructor(
    private val dispatchers: Dispatchers,
    private val onBoardingRepository: OnBoardingRepository
) : OnBoardingInteractor {

    override suspend fun getOnBoardingScreens(): List<OnBoardingEntity> = withContext(dispatchers.io) {
        onBoardingRepository.getOnBoardingScreens()
    }

}