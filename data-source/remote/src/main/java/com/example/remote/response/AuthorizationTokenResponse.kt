package com.example.remote.response

import com.google.gson.annotations.SerializedName

data class AuthorizationTokenResponse(
    @SerializedName("access_token")
    val access_token: String,

    @SerializedName("time")
    val time: Long,

    @SerializedName("refresh_token")
    val refresh_token: String
)