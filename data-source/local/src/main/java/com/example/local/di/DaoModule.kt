package com.example.local.di

import com.example.local.dao.OnBoardingDao
import com.example.local.database.ApplicationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    @Singleton
    fun provideDashBoardDao(db: ApplicationDatabase): OnBoardingDao {
        return db.getOnboardDao()
    }
}
