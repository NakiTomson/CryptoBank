package com.example.entity

import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import core.R
import java.util.Calendar

data class CardEntity(
    val id: String = "1",
    val holderName: String = "Dmitry Ryzhov",
    val number: String = "1232 3812 9872",
    val balance: String = "1,230.21",
    val paymentType: PaymentCurrencyType = PaymentCurrencyType.USD,
    val paymentSystem: PaymentSystemType = PaymentSystemType.Visa,
    val transactions: List<TransactionEntity> = listOf(
        TransactionEntity(),
        TransactionEntity(id = "2"),
        TransactionEntity(id = "3"),
        TransactionEntity(id = "4"),
        TransactionEntity(id = "5"),
        TransactionEntity(id = "6"),
        TransactionEntity(id = "7"),
        TransactionEntity(id = "8"),
        TransactionEntity(id = "9"),
        TransactionEntity(id = "10"),
    )
)

enum class PaymentCurrencyType(val currency: String) {
    USD("$"),
    EUR("€"),
    Null("₽");

    companion object {

        fun getType(type: String): PaymentCurrencyType {
            return PaymentCurrencyType.values().find { it.name.equals(type, ignoreCase = true) } ?: Null
        }
    }
}

enum class PaymentSystemType(val paySystem: Int) {
    Visa(R.drawable.ic_pay_system_visa),
    MASTERCARD(R.drawable.ic_pay_system_mastercard),
    Null(R.drawable.ic_pay_system_mir);

    companion object {
        fun getType(type: String): PaymentSystemType {
            return values().find { it.name.equals(type, true) } ?: Null
        }
    }
}

data class TransactionEntity(
    val id: String = "1",
    val cardId: String = "1",
    val name: String = "Paypal payment",
    val media: String = "https://i.ibb.co/XJpsH6p/pay-pal.png",
    val data: Calendar = Calendar.getInstance(),
    val amount: String = "216",
    val type: TransactionType = TransactionType.Income,
    val category: CategoryTransactionType = CategoryTransactionType.Recent
)

enum class TransactionType() {
    Income,
    Outcome,
    Null;

    companion object {
        fun getType(type: String): TransactionType {
            return values().find { it.name.equals(type, true) } ?: Null
        }
    }
}

enum class CategoryTransactionType(@StringRes val category: Int) {
    Recent(R.string.recent_transaction),
    Null(R.string.undefined);

    companion object {
        fun getCategory(category: String): CategoryTransactionType {
            return values().find { it.name.equals(category, true) } ?: Null
        }
    }
}