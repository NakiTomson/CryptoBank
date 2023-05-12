package com.example.impl

import com.example.api.OnBoardingRepository
import com.example.entity.OnBoardingEntity
import com.example.errors.ServerError
import com.example.local.entity.OnBoardingDb
import com.example.local.storage.OnBoardingRoomStorage
import com.example.remote.storage.OnBoardingRemoteStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(
    private val remote: OnBoardingRemoteStorage,
    private val local: OnBoardingRoomStorage,
) : OnBoardingRepository {

    private val _onBoardings: MutableSharedFlow<List<OnBoardingEntity>> = MutableStateFlow(listOf())
    override val onBoardings: Flow<List<OnBoardingEntity>> = _onBoardings.asSharedFlow()

    override suspend fun getOnBoardingScreens() {
        val result = local.getOnBoardings().run {
            if (this.isNullOrEmpty())
                local.saveOnBoarding(remote.getOnBoardingScreens().map { OnBoardingDb(it.title, it.text, it.media) })
            local.getOnBoardings()?.map { OnBoardingEntity(it.title, it.text, it.media) }
        } ?: listOf()
        _onBoardings.emit(result)
    }

}