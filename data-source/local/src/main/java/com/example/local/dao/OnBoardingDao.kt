package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.entity.OnBoardingDb

@Dao
interface OnBoardingDao {

    @Query("SELECT * FROM OnBoarding")
    suspend fun getAll(): List<OnBoardingDb>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(db: OnBoardingDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insert(dbs: List<OnBoardingDb>)
}