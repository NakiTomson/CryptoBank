package com.example.remote.storage

import com.example.errors.ServerError
import com.example.remote.response.CardResponse
import com.example.remote.response.NetworkResult
import com.example.remote.response.OnBoardingResponse
import com.example.remote.response.TransactionResponse
import com.example.remote.service.CardService
import com.example.remote.service.OnBoardingService
import com.example.remote.service.TransactionService
import javax.inject.Inject

class CardRemoteStorage @Inject constructor(
    private val cardService: CardService,
    private val transaction: TransactionService,
) {

    suspend fun getCards(id: String): List<CardResponse> {
        return when (val result = cardService.getCards()) {
            is NetworkResult.Response.Success -> result.data ?: throw ServerError.UndefinedError()
            is NetworkResult.Response.Error -> throw ServerError.UndefinedError()
            is NetworkResult.Exception -> throw ServerError.NetworkError()
        }
    }

    suspend fun getTransactions(id: String): List<TransactionResponse> {
        return when (val result = transaction.getTransactions()) {
            is NetworkResult.Response.Success -> result.data ?: throw ServerError.UndefinedError()
            is NetworkResult.Response.Error -> throw ServerError.UndefinedError()
            is NetworkResult.Exception -> throw ServerError.NetworkError()
        }
    }
}
