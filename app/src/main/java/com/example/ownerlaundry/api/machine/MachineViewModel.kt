package com.example.ownerlaundry.api.machine

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.ownerlaundry.STORE_ID
import com.example.ownerlaundry.api.menu.MenuApp
import com.example.ownerlaundry.api.menu.MenuModel
import com.example.ownerlaundry.navigation.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MachineViewModel: ViewModel() {
    var machineListResponse: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
    var stateMachine: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getMachine(){
        try {
            MachineApp.CreateInstance().fetchMachine(store = STORE_ID).enqueue(object :
                Callback<ArrayList<MachineModel>> {
                override fun onResponse(call: Call<ArrayList<MachineModel>>, response: Response<ArrayList<MachineModel>>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Code Machine : ${response.code().toString()}")
                    stateMachine = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            machineListResponse = response.body()!!
//                            MACHINE_DATA = machineListResponse
//                            Log.d("debug", "Code : ${response.code().toString()}")
//                            Log.d("debug", "Code : ${menuListResponse}")
                            stateMachine = 1
                        }
                        if (machineListResponse.isNullOrEmpty()){
                            stateMachine = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<MachineModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateMachine = 2
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

    fun insertMachine(
        number: Int,
        class_machine: Boolean,
        type_machine: Boolean,
        navController: NavController
    ){
        val bodyDataInsert = MachineModel(
            machineNumber = number,
            machineClass = class_machine,
            machineType = type_machine,
            machineStore = STORE_ID,
            isPacket = false,
            priceTime = 0,
            machineStatus = false
        )
        try {
            MachineApp.CreateInstance().insertMachine(statusData = bodyDataInsert).enqueue(object :
                Callback<MachineModel> {
                override fun onResponse(call: Call<MachineModel>, response: Response<MachineModel>) {
//                    Log.d("debug", "url : ${response}")
                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateMachine = 0
                    if(response.code() == 201){
                        navController.navigate(route = Screens.Machine.route){
                            popUpTo(Screens.Machine.route) {
                                inclusive = true
                            }
                        }
                    }
                    if(response.code() == 400){
                        stateMachine = 4
                    }
                }

                override fun onFailure(call: Call<MachineModel>, t: Throwable) {
                    Log.d("debug", "Fail Push Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateMachine = 2
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

    fun updateMachine(
        idMachine: String,
        number: Int,
        type_machine: Boolean,
        class_machine: Boolean,
        navController: NavController
    ){
        val bodyDataUpdate = MachineModel(
            machineNumber = number,
            machineType = type_machine,
            machineClass = class_machine
        )
//        val bodyUpdate = MachineModelUpdate(machineStatus = true, isPacket = isPacket, priceTime = timeMachine)

        try {
            MachineApp.CreateInstance().updateMachine(id = idMachine, bodyDataUpdate).enqueue(object :
                Callback<MachineModel> {
                override fun onResponse(call: Call<MachineModel>, response: Response<MachineModel>) {
                    Log.d("debug", "Code Update ${response.code()}")
                    if(response.code() == 200){
                        navController.navigate(route = Screens.Machine.route){
                            popUpTo(Screens.Machine.route) {
                                inclusive = true
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MachineModel>, t: Throwable) {
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

    fun deleteMachine(idMachine: String, navController: NavController){
        try {
            MachineApp.CreateInstance().deleteMachine(id = idMachine).enqueue(object :
                Callback<MachineModel> {
                override fun onResponse(call: Call<MachineModel>, response: Response<MachineModel>) {
                    Log.d("debug", "Code Update ${response.code()}")
                    if(response.code() == 200){
                        navController.navigate(route = Screens.Machine.route){
                            popUpTo(Screens.Machine.route) {
                                inclusive = true
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MachineModel>, t: Throwable) {
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