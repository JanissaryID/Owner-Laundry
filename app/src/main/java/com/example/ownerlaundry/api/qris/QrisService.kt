package com.example.ownerlaundry.api.qris

import com.example.ownerlaundry.api.machine.MachineModel
import com.example.ownerlaundry.api.store.StoreModel
import retrofit2.Call
import retrofit2.http.*

interface QrisService {
    @GET("Qris")
    fun fetchQris(
        @Query(value="qris_store", encoded=true) store: String?,
    ): Call<ArrayList<QrisModel>>

    @POST("Qris")
    fun insertQris(@Body statusData: QrisModel): Call<QrisModel>

    @PATCH("Qris/{id}")
    fun updateQris(
        @Path("id") id: String?, @Body updateData : QrisModel
    ): Call<QrisModel>

    @DELETE("Qris/{id}")
    fun deleteQris( @Path("id") id: String? ): Call<QrisModel>
}