package com.example.domain.impl

import com.example.api.OnBoardingRepository
import com.example.core.Dispatchers
import com.example.domain.api.OnBoardingInteractor
import com.example.entity.OnBoardingEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnBoardingInteractorImpl @Inject constructor(
    private val dispatchers: Dispatchers,
    private val onBoardingRepository: OnBoardingRepository
) : OnBoardingInteractor {

    override val onBoardings: Flow<List<OnBoardingEntity>> = onBoardingRepository.onBoardings

    override suspend fun getOnBoardingScreens(): Unit = withContext(dispatchers.io) {
        onBoardingRepository.getOnBoardingScreens()
    }

}