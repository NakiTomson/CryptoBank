package com.example.impl

import com.example.api.CardRepository
import com.example.entity.CardEntity
import com.example.local.storage.CardRoomStorage
import com.example.mapper.getDb
import com.example.mapper.getEntity
import com.example.remote.response.toEntity
import com.example.remote.storage.CardRemoteStorage
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val remote: CardRemoteStorage,
    private val local: CardRoomStorage,
) : CardRepository {

    override suspend fun getUserCards(userId: String): List<CardEntity> {
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
}
