package com.example.ownerlaundry.api.transaction

import retrofit2.Call
import retrofit2.http.*

interface TransactionService {
    @GET("Transaction")
    fun fetchTransaction(
        @Query(value = "\$lookup", encoded = true) lookup: String,
        @Query(value="transaction_store", encoded=true) store: String?,
        @Query(value="transaction_date", encoded=true) date: String?,
        @Query(value="transaction_finish", encoded=true) finish: Boolean?
    ): Call<ArrayList<TransactionModel>>
}