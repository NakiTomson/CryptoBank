package com.example.local.storage

import android.util.Log
import com.example.local.dao.CardDao
import com.example.local.dao.TransactionDao
import com.example.local.entity.CardDb
import com.example.local.entity.TransactionDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRoomStorage @Inject constructor(
    private val cardDao: CardDao,
    private val transactionDao: TransactionDao
) {
    fun getCardsFlow(): Flow<List<CardDb>?> {
        return cardDao.getAllCardsFlow()
    }

    fun getTransactionFlow(): Flow<List<TransactionDB>?> {
        return transactionDao.getTransactionsFlow()
    }


    suspend fun getCards(): List<CardDb>? {
        return cardDao.getAllCards()
    }

    suspend fun saveCard(db: CardDb) {
        return cardDao.insertCard(db)
    }

    suspend fun saveCards(db: List<CardDb>) {
        return cardDao.insertCard(db)
    }

    suspend fun getAllTransactions(): List<TransactionDB>? {
        return transactionDao.getAllTransactions()
    }

    suspend fun getTransactionsByCard(cardId: String): List<TransactionDB>? {
        return transactionDao.getTransactionsByCard(cardId)
    }

    suspend fun saveTransaction(transaction: TransactionDB) {
        return transactionDao.insertTransaction(transaction)
    }

    suspend fun saveTransactions(transactions: List<TransactionDB>) {
        return transactionDao.insertTransactions(transactions)
    }
}
