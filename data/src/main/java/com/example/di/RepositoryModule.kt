package com.example.di

import com.example.api.UserRepository
import com.example.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserInteractor(userInteractor: UserRepositoryImpl): UserRepository
}
