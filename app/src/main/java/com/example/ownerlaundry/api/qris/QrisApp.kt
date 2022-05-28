package com.example.ownerlaundry.api.qris

import com.example.ownerlaundry.KEY_URL
import com.example.ownerlaundry.api.store.StoreService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QrisApp {
//    private var BASE_URL = "https://api.kontenbase.com/query/api/v1/a61eb959-29ce-4c54-b5ed-72c525faf455/"
    private var BASE_URL = "https://api.kontenbase.com/query/api/v1/$KEY_URL/"

    fun CreateInstance(): QrisService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(QrisService::class.java)
    }
}