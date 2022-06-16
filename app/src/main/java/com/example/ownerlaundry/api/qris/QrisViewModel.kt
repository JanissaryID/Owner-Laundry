package com.example.ownerlaundry.api.qris

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.ownerlaundry.IS_DIALOG_OPEN
import com.example.ownerlaundry.KEY_URL
import com.example.ownerlaundry.QRIS_DATA
import com.example.ownerlaundry.STORE_ID
import com.example.ownerlaundry.api.store.StoreApp
import com.example.ownerlaundry.api.store.StoreModel
import com.example.ownerlaundry.navigation.Screens
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class QrisViewModel: ViewModel() {
    var qrisListResponse:ArrayList<QrisModel> by mutableStateOf(arrayListOf())
    var stateQris: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getQris(){
        qrisListResponse.clear()
        try {
            var viewModelJob = Job()
            val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

            uiScope.launch {
                withContext(Dispatchers.IO) {
                    while (true){
                        if (!KEY_URL.isNullOrEmpty()){
                            var response = QrisApp.CreateInstance().fetchQris(
                                store = STORE_ID
                            ).execute()
                            if(response.code() == 200) {
                                response.body()?.let {
                                    qrisListResponse = response.body()!!
                                    QRIS_DATA = qrisListResponse
                                }
                            }
                            break
                        }
                    }
                    delay(100)
                }
            }
        }
        catch (e : Exception){
//            Log.d("debug", "ERROR")
            errorMessage = e.message.toString()
        }
    }

    fun insertQris(
        clientkey: String,
        clientID: String,
        merchantID: String,
        navController: NavController
    ){
        val bodyDataInsert = QrisModel(
            clientKey = clientkey,
            clientId = clientID,
            merchantId = merchantID,
            qrisStore = STORE_ID
        )
        try {
            QrisApp.CreateInstance().insertQris(statusData = bodyDataInsert).enqueue(object :
                Callback<QrisModel> {
                override fun onResponse(call: Call<QrisModel>, response: Response<QrisModel>) {
//                    Log.d("debug", "url : ${response}")
                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateQris = 0
                    if(response.code() == 201){
                        navController.navigate(route = Screens.Menu.route){
                            popUpTo(Screens.Home.route) {
                                inclusive = true
                            }
                        }
                        IS_DIALOG_OPEN.value = false
                    }
                    if(response.code() == 400){
                        stateQris = 4
                    }
                }

                override fun onFailure(call: Call<QrisModel>, t: Throwable) {
                    Log.d("debug", "Fail Push Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateQris = 2
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

    fun updateQris(
        clientkey: String,
        clientID: String,
        merchantID: String,
        idQris: String,
        navController: NavController
    ){
        val bodyDataUpdate = QrisModel(
            clientKey = clientkey,
            clientId = clientID,
            merchantId = merchantID
        )
        try {
            QrisApp.CreateInstance().updateQris(id = idQris, updateData = bodyDataUpdate).enqueue(object :
                Callback<QrisModel> {
                override fun onResponse(call: Call<QrisModel>, response: Response<QrisModel>) {
//                    Log.d("debug", "url : ${response}")
                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateQris = 0
                    if(response.code() == 200){
                        navController.navigate(route = Screens.Menu.route){
                            popUpTo(Screens.Home.route) {
                                inclusive = true
                            }
                        }
                        IS_DIALOG_OPEN.value = false
                    }
                    if(response.code() == 400){
                        stateQris = 4
                    }
                }

                override fun onFailure(call: Call<QrisModel>, t: Throwable) {
                    Log.d("debug", "Fail Push Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateQris = 2
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
}