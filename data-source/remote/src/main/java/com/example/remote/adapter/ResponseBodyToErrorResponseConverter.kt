package com.example.remote.adapter

import com.example.remote.response.ErrorResponse
import retrofit2.Response
import retrofit2.Retrofit

class ResponseBodyToErrorResponseConverter {

    fun <T> convert(
        response: Response<T>,
        retrofit: Retrofit?
    ): ErrorResponse = ErrorResponse(
        response.errorBody(),
        response.raw().request.url.toString(),
        retrofit
    )
}