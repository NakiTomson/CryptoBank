package com.example.api

import com.example.entity.CardEntity
import com.example.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    val cardsFlow: Flow<List<CardEntity>?>

    val transactionFlow: Flow<List<TransactionEntity>>

    suspend fun fetchCards(userId: String)

    suspend fun fetchTransactions(cardId: String)
}
