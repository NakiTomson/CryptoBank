package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.entity.CardDb
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Query("SELECT * FROM card")
    fun getAllCardsFlow(): Flow<List<CardDb>?>

    @Query("SELECT * FROM card")
    suspend fun getAllCards(): List<CardDb>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(db: CardDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertCard(dbs: List<CardDb>)
}