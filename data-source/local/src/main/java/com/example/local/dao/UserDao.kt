package com.example.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.local.entity.OnBoardingDb
import com.example.local.entity.UserDb

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun get(): UserDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(db: UserDb)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(db: UserDb)

    @Delete
    suspend fun delete(db: UserDb)
}
