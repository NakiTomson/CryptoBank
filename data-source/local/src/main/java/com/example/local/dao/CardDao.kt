package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.entity.CardDb
import com.example.local.entity.OnBoardingDb

@Dao
interface CardDao {

    @Query("SELECT * FROM card")
    suspend fun getAll(): List<CardDb>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(db: CardDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insert(dbs: List<CardDb>)
}