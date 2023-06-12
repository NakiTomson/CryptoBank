package com.example.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.local.dao.CardDao
import com.example.local.dao.OnBoardingDao
import com.example.local.dao.UserDao
import com.example.local.entity.CardDb
import com.example.local.entity.OnBoardingDb
import com.example.local.entity.TransactionConverter
import com.example.local.entity.UserDb


@Database(
    entities = [OnBoardingDb::class, UserDb::class, CardDb::class],
    version = 4,
    exportSchema = true
)
@TypeConverters(TransactionConverter::class)
internal abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun getOnboardDao(): OnBoardingDao
    abstract fun getUserDao(): UserDao
    abstract fun getCardDao(): CardDao
}
