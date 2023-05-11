package com.example.remote.di

import com.example.remote.core.ServiceFactory
import com.example.remote.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Singleton
    @Provides
    fun provideAuthService(serviceFactory: ServiceFactory): AuthService =
        serviceFactory.createService(AuthService::class.java)
}
