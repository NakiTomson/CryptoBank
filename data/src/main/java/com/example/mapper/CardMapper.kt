package com.example.mapper

import com.example.core.getMockCalendarData
import com.example.entity.CardEntity
import com.example.entity.CategoryTransactionType
import com.example.entity.DesignCardEntity
import com.example.entity.PaymentCurrencyType
import com.example.entity.PaymentSystemType
import com.example.entity.TransactionEntity
import com.example.entity.TransactionType
import com.example.local.entity.CardDb
import com.example.local.entity.DesignCardDb
import com.example.local.entity.TransactionDB
import com.example.remote.response.DesignCardResponse
import java.util.Calendar

fun List<CardDb>.getEntity(): List<CardEntity> {
    return this.map { it.getEntity() }
}

fun CardDb.getEntity(): CardEntity {
    return CardEntity(
        id,
        holderName,
        number,
        balance,
        PaymentCurrencyType.getType(paymentType),
        PaymentSystemType.getType(paymentSystem),
        designCard.toEntity(),
        transactions.toEntity().sortedByDescending { it.data }.sortedBy { it.category }
    )
}

fun DesignCardDb.toEntity(): DesignCardEntity {
    return DesignCardEntity(background, textColor, paySystemLogo)
}

fun List<TransactionDB>.toEntity(): List<TransactionEntity> {
    return this.map { it.getEntity() }
}

fun TransactionDB.getEntity(): TransactionEntity {
    return TransactionEntity(
        id,
        cardId,
        name,
        media,
        getMockCalendarData(),
        amount,
        TransactionType.getType(type),
        CategoryTransactionType.getCategory(category)
    )
}


fun List<CardEntity>.getDb(): List<CardDb> {
    return this.map { it.toDb() }
}

fun CardEntity.toDb(): CardDb {
    return CardDb(
        id,
        holderName,
        number,
        balance,
        paymentType.name,
        paymentSystem.name,
        designCard.toDb(),
        transactions.toDb()
    )
}

fun DesignCardEntity.toDb(): DesignCardDb {
    return DesignCardDb(background, textColor, paySystemLogo)
}

fun List<TransactionEntity>.toDb(): ArrayList<TransactionDB> {
    return this.map { it.toDb() } as ArrayList
}

fun TransactionEntity.toDb(): TransactionDB {
    return TransactionDB(id, cardId, name, media, amount, type.name, category.name)
}
