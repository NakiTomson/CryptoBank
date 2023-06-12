package com.example.local.storage

import com.example.local.dao.CardDao
import com.example.local.dao.OnBoardingDao
import com.example.local.entity.CardDb
import com.example.local.entity.OnBoardingDb
import javax.inject.Inject

class CradRoomStorage @Inject constructor(
    private val dao: CardDao
) {
    suspend fun getCards(): List<CardDb>? {
        return dao.getAll()
    }

    suspend fun saveCard(db: CardDb) {
        return dao.insert(db)
    }

    suspend fun saveCards(db: List<CardDb>) {
        return dao.insert(db)
    }
}