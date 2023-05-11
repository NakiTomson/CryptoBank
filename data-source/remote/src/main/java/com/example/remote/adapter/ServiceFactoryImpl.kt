package com.example.remote.adapter

import com.example.remote.core.ServiceFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ServiceFactoryImpl @Inject constructor(
    private val okHttpClientBuilder: OkHttpClient.Builder,
    private val gson: Gson,
) : ServiceFactory {

    override fun <S> createService(serviceClass: Class<S>): S {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(ResultCallAdapter.Factory(ResponseBodyToErrorResponseConverter()))
            .client(okHttpClientBuilder.build())
            .build()
            .create(serviceClass)
    }

    companion object {
        const val BASE_URL = "https://645bcdcfa8f9e4d6e77393a3.mockapi.io"
    }
}
