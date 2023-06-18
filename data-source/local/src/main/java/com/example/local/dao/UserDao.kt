package com.example.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.local.entity.UserDb
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUserFlow(): Flow<UserDb?>

    @Query("SELECT * FROM user")
    suspend fun getUser(): UserDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(db: UserDb)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(db: UserDb)

    @Delete
    suspend fun deleteUser(db: UserDb)
}
