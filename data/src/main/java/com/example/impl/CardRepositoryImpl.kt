package com.example.impl

import com.example.api.CardRepository
import com.example.entity.CardEntity
import com.example.entity.CategoryTransactionType
import com.example.entity.PaymentCurrencyType
import com.example.entity.PaymentSystemType
import com.example.entity.TransactionEntity
import com.example.entity.TransactionType
import com.example.local.entity.CardDb
import com.example.local.entity.TransactionDB
import com.example.local.storage.CradRoomStorage
import com.example.remote.response.toEntity
import com.example.remote.storage.CardRemoteStorage
import java.util.Calendar
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val remote: CardRemoteStorage,
    private val local: CradRoomStorage,
) : CardRepository {

    override suspend fun getUserCards(userId: String): List<CardEntity> {
        val result = local.getCards().run {
            if (this.isNullOrEmpty()) {
                val result = remote.getCards(userId).map {
                    it to remote.getTransactions(it.id)
                }.map {
                    it.first.toEntity(it.second)
                }
                local.saveCards(result.getDb())
            }
            local.getCards()
        } ?: listOf()
        return result.getEntity()
    }
}

private fun List<CardDb>.getEntity(): List<CardEntity> {
    return this.map { it.getEntity() }
}

private fun CardDb.getEntity(): CardEntity {
    return CardEntity(
        id,
        holderName,
        number,
        balance,
        PaymentCurrencyType.getType(paymentType),
        PaymentSystemType.getType(paymentSystem),
        transactions.toEntity()
    )
}

private fun List<TransactionDB>.toEntity(): List<TransactionEntity> {
    return this.map { it.getEntity() }
}

private fun TransactionDB.getEntity(): TransactionEntity {
    return TransactionEntity(
        id,
        cardId,
        name,
        media,
        Calendar.getInstance(),
        amount,
        TransactionType.getType(type),
        CategoryTransactionType.getCategory(category)
    )
}


private fun List<CardEntity>.getDb(): List<CardDb> {
    return this.map { it.toDb() }
}

private fun CardEntity.toDb(): CardDb {
    return CardDb(id, holderName, number, balance, paymentType.name, paymentSystem.name, transactions.toDb())
}

private fun List<TransactionEntity>.toDb(): ArrayList<TransactionDB> {
    return this.map { it.toDb() } as ArrayList
}

private fun TransactionEntity.toDb(): TransactionDB {
    return TransactionDB(id, cardId, name, media, amount, type.name, category.name)
}


