package com.example.remote.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.remote.adapter.ServiceFactoryImpl
import com.example.remote.core.AppInterceptor
import com.example.remote.core.ServiceFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import remote.BuildConfig
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Singleton
    @Binds
    fun provideServiceClient(serviceFactoryImpl: ServiceFactoryImpl): ServiceFactory

    @Singleton
    @Binds
    fun provideAuthInterceptor(appInterceptor: AppInterceptor): Interceptor

    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModuleObject {

        private const val httpCacheSizeBytes = 1024 * 1024 * 50L
        private const val connectTimeout = 30000L
        private const val networkTimeout = 10000L

        @Provides
        @Singleton
        fun provideGson(): Gson = GsonBuilder().create()

        @Provides
        @Singleton
        fun httpCache(@ApplicationContext context: Context): Cache = Cache(context.cacheDir, httpCacheSizeBytes)

        @Provides
        @Singleton
        fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else level
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(
            @ApplicationContext context: Context,
            cache: Cache,
            authInterceptor: Interceptor,
            loggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient.Builder {
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(networkTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(networkTimeout, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .followRedirects(true)
                .followSslRedirects(true)
                .cache(cache)
                .addInterceptor(ChuckerInterceptor(context))
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
            return okHttpClientBuilder
        }

    }

}

