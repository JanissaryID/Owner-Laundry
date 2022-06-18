package com.example.ownerlaundry.api.machine

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ownerlaundry.IS_DIALOG_OPEN
import com.example.ownerlaundry.MENU_ID
import com.example.ownerlaundry.QRIS_ID
import com.example.ownerlaundry.STORE_ID
import com.example.ownerlaundry.api.menu.MenuApp
import com.example.ownerlaundry.api.menu.MenuModel
import com.example.ownerlaundry.api.menu.MenuViewModel
import com.example.ownerlaundry.api.price.PriceApp
import com.example.ownerlaundry.api.price.PriceInsertModel
import com.example.ownerlaundry.api.price.PriceModel
import com.example.ownerlaundry.api.qris.QrisViewModel
import com.example.ownerlaundry.navigation.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MachineViewModel: ViewModel() {
    var machineListResponse: ArrayList<MachineModel> by mutableStateOf(arrayListOf())
    var stateMachine: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")
    var deleteAllMachine: Boolean by mutableStateOf(false)

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
                        IS_DIALOG_OPEN.value = false
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
                        IS_DIALOG_OPEN.value = false
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
                        IS_DIALOG_OPEN.value = false
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

    fun deleteMachineInStore(idMachine: String){
        try {
            MachineApp.CreateInstance().deleteMachine(id = idMachine).enqueue(object :
                Callback<MachineModel> {
                override fun onResponse(call: Call<MachineModel>, response: Response<MachineModel>) {
                    Log.d("debug", "Code Delete ${response.code()}")
                    if(response.code() != 200){
                        deleteMachineInStore(idMachine = idMachine)
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

    fun getIDMachine(navController: NavController){
        try {
            MachineApp.CreateInstance().fetchIdMachine(lookup = "*", store = STORE_ID).enqueue(object :
                Callback<ArrayList<MachineModel>> {
                override fun onResponse(call: Call<ArrayList<MachineModel>>, response: Response<ArrayList<MachineModel>>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateMachine = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            machineListResponse = response.body()!!
//                            Log.d("debug", "Price Response : ${machineListResponse.size}")
//                            Log.d("debug", "Price Response : ${response.body()}")
                            if (!machineListResponse.isNullOrEmpty()){
                                for (item in machineListResponse){
//                                    Log.d("debug", "Price Response : ${item}")
                                    deleteMachineInStore(idMachine = item.id!!)
                                }
                                deleteAllMachine = true
                                moveDeleteQris(navController = navController)
                            }
                            else{
                                deleteAllMachine = true
                                moveDeleteQris(navController = navController)
                            }
//                            Log.d("debug", "State Empty Machine : ${deleteAllMachine}")
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

    fun moveDeleteQris(qrisViewModel: QrisViewModel = QrisViewModel(), navController: NavController){
        if(deleteAllMachine){
            Log.d("debug", "Delete Machine Success, then move to delete Qris")
            qrisViewModel.getQris(navController = navController, deleteAll = true)
        }
    }
}