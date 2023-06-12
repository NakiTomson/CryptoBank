package com.example.di

import com.example.api.CardRepository
import com.example.api.OnBoardingRepository
import com.example.api.UserRepository
import com.example.impl.CardRepositoryImpl
import com.example.impl.OnBoardingRepositoryImpl
import com.example.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userInteractor: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindOnBoardingRepository(userInteractor: OnBoardingRepositoryImpl): OnBoardingRepository

    @Binds
    abstract fun bindCardRepository(userInteractor: CardRepositoryImpl): CardRepository
}
