package com.example.remote.service


import com.example.remote.response.NetworkResult
import com.example.remote.response.OnBoardingResponse
import retrofit2.http.GET

interface OnBoardingService {

    @GET("api/v2/onboarding")
    suspend fun getOnBoardingScreens(): NetworkResult<List<OnBoardingResponse>>
}