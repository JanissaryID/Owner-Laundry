package com.example.ownerlaundry.api.price

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.ownerlaundry.STORE_ID
import com.example.ownerlaundry.api.machine.MachineApp
import com.example.ownerlaundry.api.machine.MachineModel
import com.example.ownerlaundry.api.menu.MenuApp
import com.example.ownerlaundry.api.menu.MenuModel
import com.example.ownerlaundry.navigation.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class PriceViewModel: ViewModel() {
    var priceListResponse: ArrayList<PriceModel> by mutableStateOf(arrayListOf())
    var statePrice: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getPrice(){
        try {
            PriceApp.CreateInstance().fetchPrice(lookup = "*",store = STORE_ID).enqueue(object :
                Callback<ArrayList<PriceModel>> {
                override fun onResponse(call: Call<ArrayList<PriceModel>>, response: Response<ArrayList<PriceModel>>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Code : ${response.code().toString()}")
                    statePrice = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            priceListResponse = response.body()!!
//                            MACHINE_DATA = machineListResponse
//                            Log.d("debug", "Code : ${response.code().toString()}")
//                            Log.d("debug", "Price Response : ${priceListResponse}")
                            statePrice = 1
                        }
                        if (priceListResponse.isNullOrEmpty()){
                            statePrice = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<PriceModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        statePrice = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            errorMessage = e.message.toString()
            Log.d("debug", "ERROR $errorMessage")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun insertPrice(
        dryerNormal: Boolean,
        isPacket: Boolean,
        price: String,
        priceTime: Int,
        menuID: String,
        classMachine: Boolean,
        priceTitle: String,
        navController: NavController
    ){
        val bodyDataInsert = PriceInsertModel(
            menu = listOf(menuID) ,
            isPacket = isPacket,
            priceTime = priceTime,
            dryerNormal = dryerNormal,
            price = price,
            priceStore = STORE_ID,
            priceTitle = priceTitle,
            priceClassMachine = classMachine
        )
        try {
            PriceApp.CreateInstance().insertPrice(statusData = bodyDataInsert).enqueue(object :
                Callback<PriceInsertModel> {
                override fun onResponse(call: Call<PriceInsertModel>, response: Response<PriceInsertModel>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Body Data : ${bodyDataInsert}")
//                    Log.d("debug", "Code : ${response}")
                    statePrice = 0
                    if(response.code() == 201){
                        navController.navigate(route = Screens.Price.route){
                            popUpTo(Screens.Price.route) {
                                inclusive = true
                            }
                        }
                    }
                    if(response.code() == 400){
                        statePrice = 4
                    }
                }

                override fun onFailure(call: Call<PriceInsertModel>, t: Throwable) {
                    Log.d("debug", "Fail Push Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        statePrice = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            errorMessage = e.message.toString()
            Log.d("debug", "ERROR $errorMessage")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun updatePrice(
        idPrice: String,
        dryerNormal: Boolean,
        isPacket: Boolean,
        price: String,
        priceTime: Int,
        menuID: String,
        classMachine: Boolean,
        priceTitle: String,
        navController: NavController
    ){
        val bodyDataInsert = PriceInsertModel(
            menu = listOf(menuID) ,
            isPacket = isPacket,
            priceTime = priceTime,
            dryerNormal = dryerNormal,
            price = price,
            priceStore = STORE_ID,
            priceTitle = priceTitle,
            priceClassMachine = classMachine
        )

        try {
            Log.d("debug", "id Price ${idPrice}")
            PriceApp.CreateInstance().updatePrice(id = idPrice, bodyDataInsert).enqueue(object :
                Callback<PriceInsertModel> {
                override fun onResponse(call: Call<PriceInsertModel>, response: Response<PriceInsertModel>) {
//                    Log.d("debug", "Code Update ${response.code()}")
                    if(response.code() == 200){
                        navController.navigate(route = Screens.Price.route){
                            popUpTo(Screens.Price.route) {
                                inclusive = true
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<PriceInsertModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            errorMessage = e.message.toString()
            Log.d("debug", "ERROR $errorMessage")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun deletePrice(idPrice: String, navController: NavController){
        try {
            PriceApp.CreateInstance().deletePrice(id = idPrice).enqueue(object :
                Callback<PriceInsertModel> {
                override fun onResponse(call: Call<PriceInsertModel>, response: Response<PriceInsertModel>) {
                    Log.d("debug", "Code Delete ${response.code()}")
                    if(response.code() == 200){
                        navController.navigate(route = Screens.Price.route){
                            popUpTo(Screens.Price.route) {
                                inclusive = true
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<PriceInsertModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            errorMessage = e.message.toString()
            Log.d("debug", "ERROR $errorMessage")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }
}