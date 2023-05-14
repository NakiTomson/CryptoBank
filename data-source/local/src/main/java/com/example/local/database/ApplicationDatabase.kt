package com.example.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.local.dao.OnBoardingDao
import com.example.local.dao.UserDao
import com.example.local.entity.OnBoardingDb
import com.example.local.entity.UserDb

@Database(entities = [OnBoardingDb::class, UserDb::class], version = 2, exportSchema = true)
internal abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun getOnboardDao(): OnBoardingDao
    abstract fun getUserDao(): UserDao
}
