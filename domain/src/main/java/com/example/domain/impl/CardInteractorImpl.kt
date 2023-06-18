package com.example.domain.impl

import com.example.api.CardRepository
import com.example.core.Dispatchers
import com.example.domain.api.CardInteractor
import com.example.entity.CardEntity
import com.example.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardInteractorImpl @Inject constructor(
    private val cardRepository: CardRepository,
    private val dispatchers: Dispatchers
) : CardInteractor {

    override val cardsFlow: Flow<List<CardEntity>?> = cardRepository.cardsFlow

    override suspend fun getCards(userId: String): List<CardEntity> {
        return withContext(dispatchers.io) {
            cardRepository.getCards(userId)
        }
    }
}
