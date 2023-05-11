package com.example.remote.request

import com.google.gson.annotations.SerializedName

data class SessionRequest(@SerializedName("device_id") val deviceId: String)