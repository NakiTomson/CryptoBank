package com.example.local.di

import com.example.local.dao.CardDao
import com.example.local.dao.OnBoardingDao
import com.example.local.dao.UserDao
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

    @Provides
    @Singleton
    fun provideUserDao(db: ApplicationDatabase): UserDao {
        return db.getUserDao()
    }

    @Provides
    @Singleton
    fun provideCardDao(db: ApplicationDatabase): CardDao {
        return db.getCardDao()
    }
}
