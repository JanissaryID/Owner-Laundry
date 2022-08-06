package com.example.ownerlaundry.api.debug

import com.example.ownerlaundry.api.transaction.TransactionModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DebugService {

    @GET("Debug")
    fun fetchDebug(
        @Query(value = "\$lookup", encoded = true) lookup: String,
        @Query(value="date", encoded=true) date: String?,
    ): Call<ArrayList<DebugModel>>
}