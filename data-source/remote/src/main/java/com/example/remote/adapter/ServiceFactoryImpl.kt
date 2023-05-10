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
//    private val configuration: Configuration
) : ServiceFactory {

    override fun <S> createService(serviceClass: Class<S>): S {
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(ResultCallAdapter.Factory(ResponseBodyToErrorResponseConverter()))
            .client(okHttpClientBuilder.build())
            .build()
            .create(serviceClass)
    }

}

