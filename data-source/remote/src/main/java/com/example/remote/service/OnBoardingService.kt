package com.example.remote.service


import com.example.remote.response.NetworkResult
import com.example.remote.response.OnBoardingResponse
import retrofit2.http.GET

interface OnBoardingService {

    @GET("https://645bcdcfa8f9e4d6e77393a3.mockapi.io/api/v2/onboarding")
    suspend fun getOnBoardingScreens(): NetworkResult<List<OnBoardingResponse>>
}