package com.example.domain.di

import com.example.domain.api.OnBoardingInteractor
import com.example.domain.api.UserInteractor
import com.example.domain.impl.OnBoardingInteractorImpl
import com.example.domain.impl.UserInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Binds
    abstract fun bindUserInteractor(userInteractor: UserInteractorImpl): UserInteractor

    @Binds
    abstract fun bindOnBoardingInteractor(userInteractor: OnBoardingInteractorImpl): OnBoardingInteractor
}
