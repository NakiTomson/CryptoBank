package com.example.remote.storage


import com.example.entity.OnBoardingEntity
import com.example.errors.ServerError
import com.example.remote.response.NetworkResult
import com.example.remote.response.OnBoardingResponse
import com.example.remote.service.OnBoardingService
import javax.inject.Inject

class OnBoardingRemoteStorage @Inject constructor(
    private val service: OnBoardingService,
) {
    suspend fun getOnBoardingScreens(): List<OnBoardingResponse> {
        return when (val result = service.getOnBoardingScreens()) {
            is NetworkResult.Response.Success -> result.data ?: throw ServerError.UndefinedError()
            is NetworkResult.Response.Error -> throw ServerError.UndefinedError()
            is NetworkResult.Exception -> throw ServerError.NetworkError()
        }
    }
}