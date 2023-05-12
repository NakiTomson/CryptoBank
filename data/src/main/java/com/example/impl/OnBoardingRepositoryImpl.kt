package com.example.impl

import com.example.api.OnBoardingRepository
import com.example.entity.OnBoardingEntity
import com.example.errors.ServerError
import com.example.local.entity.OnBoardingDb
import com.example.local.storage.OnBoardingRoomStorage
import com.example.remote.storage.OnBoardingRemoteStorage
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(
    private val remote: OnBoardingRemoteStorage,
    private val local: OnBoardingRoomStorage,
) : OnBoardingRepository {

    override suspend fun getOnBoardingScreens(): List<OnBoardingEntity> {
        val result = local.getOnBoardings().run {
            if (this.isNullOrEmpty())
                local.saveOnBoarding(remote.getOnBoardingScreens().map { OnBoardingDb(it.title, it.text, it.media) })
            local.getOnBoardings()?.map { OnBoardingEntity(it.title, it.text, it.media) }
        } ?: listOf()
        return result
    }

}