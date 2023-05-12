package com.example.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.local.dao.OnBoardingDao
import com.example.local.entity.OnBoardingDb

@Database(entities = [OnBoardingDb::class], version = 1, exportSchema = true)
internal abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun getOnboardDao(): OnBoardingDao
}
