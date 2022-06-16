package com.example.ownerlaundry.api.store

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.ownerlaundry.IS_DIALOG_OPEN
import com.example.ownerlaundry.api.machine.MachineApp
import com.example.ownerlaundry.api.machine.MachineModel
import com.example.ownerlaundry.navigation.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class StoreViewModel: ViewModel() {
    var storeListResponse:List<StoreModel> by mutableStateOf(listOf())
    var stateStore: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getStore(){
        try {
            StoreApp.CreateInstance().fetchStore().enqueue(object :
                Callback<List<StoreModel>> {
                override fun onResponse(call: Call<List<StoreModel>>, response: Response<List<StoreModel>>) {
                    Log.d("debug", "Store response : ${response}")
//                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateStore = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            storeListResponse = response.body()!!
//                            MACHINE_DATA = machineListResponse
//                            Log.d("debug", "Code : ${response.code().toString()}")
//                            Log.d("debug", "Code : ${storeListResponse}")
                            stateStore = 1
                        }
                        if (storeListResponse.isNullOrEmpty()){
                            stateStore = 3
                        }
                    }
                }

                override fun onFailure(call: Call<List<StoreModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateStore = 2
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

    fun insertStore(
        name: String,
        address: String,
        city: String,
        password: String,
        navController: NavController
    ){
        val bodyDataInsert = StoreModel(
            storeName = name,
            storeAddress = address,
            storeCity = city,
            passwordOwner = password
        )
        try {
            StoreApp.CreateInstance().insertStore(statusData = bodyDataInsert).enqueue(object :
                Callback<StoreModel> {
                override fun onResponse(call: Call<StoreModel>, response: Response<StoreModel>) {
//                    Log.d("debug", "url : ${response}")
                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateStore = 0
                    if(response.code() == 201){
                        navController.navigate(route = Screens.Home.route){
                            popUpTo(Screens.Home.route) {
                                inclusive = true
                            }
                        }
                        IS_DIALOG_OPEN.value = false
                    }
                    if(response.code() == 400){
                        stateStore = 4
                    }
                }

                override fun onFailure(call: Call<StoreModel>, t: Throwable) {
                    Log.d("debug", "Fail Push Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateStore = 2
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

    fun updateStore(
        storeId: String,
        storeName: String,
        storeCity: String,
        storeAddress: String,
        storePassword: String,
        navController: NavController
    ){
        val bodyDataUpdate = StoreModel(
            storeName = storeName,
            storeAddress = storeAddress,
            storeCity = storeCity,
            passwordOwner = storePassword
        )
        try {
            StoreApp.CreateInstance().updateStore(id = storeId, bodyDataUpdate).enqueue(object :
                Callback<StoreModel> {
                override fun onResponse(call: Call<StoreModel>, response: Response<StoreModel>) {
                    Log.d("debug", "Code Update ${response.code()}")
                    if(response.code() == 200){
                        navController.navigate(route = Screens.Menu.route){
                            popUpTo(Screens.Menu.route) {
                                inclusive = true
                            }
                        }
                        IS_DIALOG_OPEN.value = false
                    }
                    if(response.code() == 400){
                        stateStore = 4
                    }
                }

                override fun onFailure(call: Call<StoreModel>, t: Throwable) {
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