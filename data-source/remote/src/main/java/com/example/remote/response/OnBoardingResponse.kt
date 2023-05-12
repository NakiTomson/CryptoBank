package com.example.remote.response

import com.google.gson.annotations.SerializedName

data class OnBoardingResponse(
    @SerializedName("title") val title: String,
    @SerializedName("text") val text: String,
    @SerializedName("url") val media: String
)