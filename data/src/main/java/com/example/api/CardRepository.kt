package com.example.api

import com.example.entity.CardEntity

interface CardRepository {

    suspend fun getUserCards(userId: String): List<CardEntity>

}