package com.example.presentation.ui.bottombar.tabs.home.dto.transaction

import com.example.entity.CategoryTransactionType


data class BankTransactionCategory(val category: CategoryTransactionType) : BaseBankTransaction() {
    override val id: Int
        get() = category?.name.hashCode()
}
