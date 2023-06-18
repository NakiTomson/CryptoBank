package com.example.local.di

import android.content.Context
import androidx.room.Room
import com.example.local.database.ApplicationDatabase
import com.example.local.database.MIGRATION_1_2
import com.example.local.database.MIGRATION_2_3
import com.example.local.database.MIGRATION_3_4
import com.example.local.database.MIGRATION_4_5
import com.example.local.database.MIGRATION_5_6
import com.example.local.database.MIGRATION_6_7
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): ApplicationDatabase {
        return Room.databaseBuilder(context, ApplicationDatabase::class.java, "cryptoApp.db")
//            .fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .addMigrations(MIGRATION_3_4)
            .addMigrations(MIGRATION_4_5)
            .addMigrations(MIGRATION_5_6)
            .addMigrations(MIGRATION_6_7)
            .build()
    }

}
