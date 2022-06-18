package com.example.ownerlaundry.api.store

import com.example.ownerlaundry.api.machine.MachineModel
import com.example.ownerlaundry.api.qris.QrisModel
import retrofit2.Call
import retrofit2.http.*

interface StoreService {
    @GET("Store")
    fun fetchStore(): Call<List<StoreModel>>

//    @GET("Store/{id}")
//    fun getStore(@Path("id") id: String?): Call<StoreModel>

    @POST("Store")
    fun insertStore(@Body statusData: StoreModel): Call<StoreModel>

//    @PATCH("Store")
//    fun updateStore(): Call<List<StoreModel>>

    @PATCH("Store/{id}")
    fun updateStore(
        @Path("id") id: String?, @Body updateData : StoreModel
    ): Call<StoreModel>

    @DELETE("Store/{id}")
    fun deleteStore( @Path("id") id: String? ): Call<StoreModel>
}