package com.example.ownerlaundry.api.price

import com.example.ownerlaundry.api.menu.MenuModel
import retrofit2.Call
import retrofit2.http.*

interface PriceService {
    @GET("Price")
    fun fetchPrice(
        @Query(value = "\$lookup", encoded = true) lookup: String,
        @Query(value="price_store", encoded=true) store: String?,
    ): Call<ArrayList<PriceModel>>

    @GET("Price")
    fun fetchIdPrice(
        @Query(value = "\$lookup", encoded = true) lookup: String,
        @Query(value = "Menu[0]", encoded = true) menu: String?,
    ): Call<ArrayList<PriceModel>>

    @POST("Price")
    fun insertPrice(@Body statusData: PriceInsertModel): Call<PriceInsertModel>

    @PATCH("Price/{id}")
    fun updatePrice(
        @Path("id") id: String?, @Body updateData : PriceInsertModel
    ): Call<PriceInsertModel>

    @DELETE("Price/{id}")
    fun deletePrice( @Path("id") id: String? ): Call<PriceInsertModel>
}