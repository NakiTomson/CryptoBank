package com.example.domain.api

import com.example.entity.CardEntity
import com.example.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface CardInteractor {

    val cardsFlow: Flow<List<CardEntity>?>

    suspend fun getCards(userId: String): List<CardEntity>
}