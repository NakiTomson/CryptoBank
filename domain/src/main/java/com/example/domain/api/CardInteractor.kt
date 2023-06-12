package com.example.domain.api

import com.example.entity.CardEntity

interface CardInteractor {

    suspend fun getUserCards(userId: String): List<CardEntity>
}