package com.example.remote.response

import com.example.core.getMockCalendarData
import com.example.entity.CardEntity
import com.example.entity.CategoryTransactionType
import com.example.entity.PaymentCurrencyType
import com.example.entity.PaymentSystemType
import com.example.entity.TransactionEntity
import com.example.entity.TransactionType
import com.google.gson.annotations.SerializedName
import java.util.Calendar

data class CardResponse(
    @SerializedName("id") val id: String,
    @SerializedName("holderName") val holderName: String,
    @SerializedName("number") val number: String,
    @SerializedName("balance") val balance: String,
    @SerializedName("paymentType") val paymentType: String,
    @SerializedName("paymentSystem") val paymentSystem: String
)

fun CardResponse.toEntity(transaction: List<TransactionResponse>): CardEntity {
    return CardEntity(
        id,
        holderName,
        number,
        balance,
        PaymentCurrencyType.getType(paymentType),
        PaymentSystemType.getType(paymentSystem),
        transaction.toEntity(),
    )
}

fun List<TransactionResponse>.toEntity(): List<TransactionEntity> = this.map { it.toEntity() }

fun TransactionResponse.toEntity(): TransactionEntity {
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
