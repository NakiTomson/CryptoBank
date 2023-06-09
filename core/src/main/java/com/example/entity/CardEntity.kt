package com.example.entity

import androidx.annotation.StringRes
import com.example.core.getMockCalendarData
import core.R
import java.util.Calendar

data class CardEntity(
    val id: String = "1",
    val holderName: String = "Dmitry Ryzhov",
    val number: String = "1232 3812 9872",
    val balance: String = "1,230.21",
    val paymentType: PaymentCurrencyType = PaymentCurrencyType.USD,
    val paymentSystem: PaymentSystemType = PaymentSystemType.Visa,
    val designCard: DesignCardEntity =
        DesignCardEntity("", "#f9f9f8", ""),
)

class DesignCardEntity(
    val background: String,
    val textColor: String,
    val paySystemLogo: String
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
    val data: Calendar = getMockCalendarData(),
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
    Others(R.string.others);

    companion object {
        fun getCategory(category: String): CategoryTransactionType {
            return values().find { it.name.equals(category, true) } ?: Others
        }
    }
}