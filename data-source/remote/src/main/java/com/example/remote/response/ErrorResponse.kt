package com.example.remote.response

import okhttp3.ResponseBody
import retrofit2.Retrofit

data class ErrorResponse(val errorResponseBody: ResponseBody?, val url: String, val retrofit: Retrofit?)