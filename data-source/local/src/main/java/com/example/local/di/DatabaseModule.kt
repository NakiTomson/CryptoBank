package com.example.local.di

import android.content.Context
import com.example.local.database.ApplicationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    fun applicationDatabase(@ApplicationContext applicationContext: Context) =
        ApplicationDatabase.create(applicationContext)
}
