package com.example.ownerlaundry.api.machine

import com.example.ownerlaundry.api.menu.MenuModel
import com.example.ownerlaundry.api.price.PriceModel
import retrofit2.Call
import retrofit2.http.*

interface MachineService {
    @GET("Machine")
    fun fetchMachine(
        @Query(value="machine_store", encoded=true) store: String?,
    ): Call<ArrayList<MachineModel>>

    @GET("Machine")
    fun fetchIdMachine(
        @Query(value = "\$lookup", encoded = true) lookup: String,
        @Query(value="machine_store", encoded=true) store: String?
    ): Call<ArrayList<MachineModel>>

    @POST("Machine")
    fun insertMachine(@Body statusData: MachineModel): Call<MachineModel>

    @PATCH("Machine/{id}")
    fun updateMachine(
        @Path("id") id: String?, @Body updateData : MachineModel
    ): Call<MachineModel>

    @DELETE("Machine/{id}")
    fun deleteMachine( @Path("id") id: String? ): Call<MachineModel>
}