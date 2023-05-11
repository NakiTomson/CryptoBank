package com.example.remote.service

import com.example.remote.request.SessionRequest
import com.example.remote.response.AuthorizationTokenResponse
import com.example.remote.response.NetworkResult
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("v2/authorization/create-session")
    suspend fun getSessionToken(@Body request: SessionRequest): NetworkResult<AuthorizationTokenResponse>
}
