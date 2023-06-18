package com.example.api

import com.example.entity.CardEntity
import com.example.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    val cardsFlow: Flow<List<CardEntity>?>

    suspend fun getCards(userId: String): List<CardEntity>

    suspend fun getTransactions(cardId: String): List<TransactionEntity>

}