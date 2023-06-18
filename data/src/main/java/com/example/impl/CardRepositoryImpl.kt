package com.example.impl

import android.util.Log
import com.example.api.CardRepository
import com.example.entity.CardEntity
import com.example.entity.TransactionEntity
import com.example.local.entity.CardDb
import com.example.local.storage.CardRoomStorage
import com.example.mapper.getDb
import com.example.mapper.getEntity
import com.example.mapper.toDb
import com.example.mapper.toEntity
import com.example.remote.response.toEntity
import com.example.remote.storage.CardRemoteStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val remote: CardRemoteStorage,
    private val local: CardRoomStorage,
) : CardRepository {

    private val _transactionFlow: MutableStateFlow<List<TransactionEntity>> = MutableStateFlow(emptyList())
    override val transactionFlow: Flow<List<TransactionEntity>> = _transactionFlow.asStateFlow()

    override val cardsFlow: Flow<List<CardEntity>?> = local.getCardsFlow().map { it?.getEntity() }

    override suspend fun fetchCards(userId: String) {
        val newCards = remote.getCards(userId).map { it.toEntity() }.getDb()
        local.saveCards(newCards)
    }

    override suspend fun fetchTransactions(cardId: String) {
        val result = local.getAllTransactions().run {
            if (this.isNullOrEmpty()) {
                val transaction = remote.getTransactions(cardId).map { it.toEntity() }.toDb()
                local.saveTransactions(transaction)
            }
            local.getTransactionsByCard(cardId)
        } ?: listOf()
        _transactionFlow.emit(result.map { it.getEntity() })
    }
}
