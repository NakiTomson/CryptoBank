package com.example.remote.service

import com.example.remote.response.NetworkResult
import com.example.remote.response.TransactionResponse
import retrofit2.http.GET

interface TransactionService {

    @GET("https://6457be571a4c152cf98956b5.mockapi.io/transactions")
    suspend fun getTransactions(): NetworkResult<List<TransactionResponse>>
}