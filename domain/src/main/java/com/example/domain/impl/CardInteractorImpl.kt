package com.example.domain.impl

import android.util.Log
import com.example.api.CardRepository
import com.example.core.Dispatchers
import com.example.domain.api.CardInteractor
import com.example.entity.CardEntity
import com.example.entity.TransactionEntity
import com.example.mapper.toDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardInteractorImpl @Inject constructor(
    private val cardRepository: CardRepository,
    private val dispatchers: Dispatchers
) : CardInteractor {

    override val cardsFlow: Flow<List<CardEntity>?> = cardRepository.cardsFlow

    override val transactionFlow: Flow<List<TransactionEntity>> = cardRepository.transactionFlow

    override suspend fun fetchCards(userId: String): Unit = withContext(dispatchers.io) {
        cardRepository.fetchCards(userId)
    }

    override suspend fun fetchTransactions(cardId: String): Unit = withContext(dispatchers.io) {
        cardRepository.fetchTransactions(cardId)
    }
}
