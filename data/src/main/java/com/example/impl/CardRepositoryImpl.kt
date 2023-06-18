package com.example.impl

import android.util.Log
import com.example.api.CardRepository
import com.example.entity.CardEntity
import com.example.entity.TransactionEntity
import com.example.local.entity.CardDb
import com.example.local.storage.CardRoomStorage
import com.example.mapper.getDb
import com.example.mapper.getEntity
import com.example.remote.response.toEntity
import com.example.remote.storage.CardRemoteStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val remote: CardRemoteStorage,
    private val local: CardRoomStorage,
) : CardRepository {

    override val cardsFlow: Flow<List<CardEntity>?> = local.getCardsFlow().map { it?.getEntity() }

    override suspend fun getCards(userId: String): List<CardEntity> {
        val result = local.getCards().run {
            if (this.isNullOrEmpty()) {
                val cards = remote.getCards(userId)
                val transactions = remote.getTransactions(userId)
                val result = cards
                    .map { card ->
                        card to (transactions.filter { it.cardId == card.id })
                    }.map {
                        it.first.toEntity(it.second)
                    }.getDb()
                local.saveCards(result)
            }
            local.getCards()
        } ?: listOf()
        return result.getEntity()
    }

    override suspend fun getTransactions(cardId: String): List<TransactionEntity> {
       return remote.getTransactions(cardId).map { it.toEntity() }
    }
}
