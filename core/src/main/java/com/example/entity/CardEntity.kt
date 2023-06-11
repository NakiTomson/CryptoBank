package com.example.entity

import java.util.Calendar

data class CardEntity(
    val id: String = "1",
    val holderName: String = "Dmitry Ryzhov",
    val number: String = "1232 3812 9872",
    val balance: String = "1,230.21",
    val paymentType: PaymentCurrencyType = PaymentCurrencyType.USD,
    val paymentSystem: PaymentSystemType = PaymentSystemType.Visa,
    val transactions: List<Transaction> = listOf(
        Transaction(),
        Transaction(id = "2"),
        Transaction(id = "3"),
        Transaction(id = "4"),
        Transaction(id = "5"),
        Transaction(id = "6"),
        Transaction(id = "7"),
        Transaction(id = "8"),
        Transaction(id = "9"),
        Transaction(id = "10"),
    )
)

enum class PaymentCurrencyType {
    USD
}

enum class PaymentSystemType {
    Visa
}

data class Transaction(
    val id: String = "1",
    val name: String = "Paypal payment",
    val icon: String = "https://i.ibb.co/XJpsH6p/pay-pal.png",
    val data: Calendar = Calendar.getInstance(),
    val amount: Double = 100.0,
    val type: TransactionType = TransactionType.Outcome,
    val category: CategoryTransactionType = CategoryTransactionType.Recent
)

enum class TransactionType {
    Income,
    Outcome
}

enum class CategoryTransactionType {
    Recent
}