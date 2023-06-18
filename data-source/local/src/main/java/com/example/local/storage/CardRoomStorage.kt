package com.example.local.storage

import android.util.Log
import com.example.local.dao.CardDao
import com.example.local.entity.CardDb
import com.example.local.entity.TransactionDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRoomStorage @Inject constructor(
    private val dao: CardDao
) {
    fun getCardsFlow(): Flow<List<CardDb>?> {
        return dao.getAllCardsFlow()
    }

    suspend fun getCards(): List<CardDb>? {
        return dao.getAllCards()
    }

    suspend fun saveCard(db: CardDb) {
        return dao.insertCard(db)
    }

    suspend fun saveCards(db: List<CardDb>) {
        return dao.insertCard(db)
    }

}