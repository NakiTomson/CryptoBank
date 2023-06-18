package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.entity.TransactionDB
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM bankTransaction")
    fun getTransactionsFlow(): Flow<List<TransactionDB>?>

    @Query("SELECT * FROM bankTransaction")
    suspend fun getAllTransactions(): List<TransactionDB>?

    @Query("SELECT * FROM bankTransaction WHERE cardId=:id ")
    suspend fun getTransactionsByCard(id: String): List<TransactionDB>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: TransactionDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransactions(transactions: List<TransactionDB>)
}
