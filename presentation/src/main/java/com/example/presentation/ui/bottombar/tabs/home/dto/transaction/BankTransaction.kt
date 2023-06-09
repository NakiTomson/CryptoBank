package com.example.presentation.ui.bottombar.tabs.home.dto.transaction

import com.example.entity.TransactionEntity

data class BankTransaction(val entity: TransactionEntity) : BaseBankTransaction() {
    override val id: String
        get() = entity.id
}