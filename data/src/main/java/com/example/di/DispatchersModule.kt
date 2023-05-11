package com.example.di

import com.example.core.Dispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    fun provideDispatchers(): Dispatchers {
        return object : Dispatchers {
            override val io: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
            override val main: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
            override val compute: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Default
            override val unconfined: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Unconfined
        }
    }
}
