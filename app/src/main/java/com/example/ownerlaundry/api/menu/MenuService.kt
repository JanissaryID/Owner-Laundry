package com.example.ownerlaundry.api.menu

import com.example.ownerlaundry.api.machine.MachineModel
import com.example.ownerlaundry.api.qris.QrisModel
import retrofit2.Call
import retrofit2.http.*

interface MenuService {
    @GET("Menu")
    fun fetchMenu(
        @Query(value="menu_store", encoded=true) store: String?,
    ): Call<ArrayList<MenuModel>>

    @GET("Menu")
    fun fetchIdMenu(
        @Query(value = "\$lookup", encoded = true) lookup: String,
        @Query(value="menu_store", encoded=true) store: String?
    ): Call<ArrayList<MenuModel>>

    @POST("Menu")
    fun insertMenu(@Body statusData: MenuModel): Call<MenuModel>

    @PATCH("Menu/{id}")
    fun updateMenu(
        @Path("id") id: String?, @Body updateData : MenuModel
    ): Call<MenuModel>

    @DELETE("Menu/{id}")
    fun deleteMenu( @Path("id") id: String? ): Call<MenuModel>
}