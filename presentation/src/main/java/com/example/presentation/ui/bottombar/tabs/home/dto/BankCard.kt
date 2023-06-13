package com.example.presentation.ui.bottombar.tabs.home.dto

import com.example.entity.CardEntity
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BaseBankTransaction

class BankCard(
    val card: CardEntity,
    val transactions: List<BaseBankTransaction>
)