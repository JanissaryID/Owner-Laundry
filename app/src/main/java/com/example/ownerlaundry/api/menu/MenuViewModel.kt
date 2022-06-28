package com.example.ownerlaundry.api.menu

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.api.machine.MachineApp
import com.example.ownerlaundry.api.machine.MachineModel
import com.example.ownerlaundry.api.price.PriceViewModel
import com.example.ownerlaundry.api.qris.QrisApp
import com.example.ownerlaundry.api.qris.QrisModel
import com.example.ownerlaundry.api.store.StoreApp
import com.example.ownerlaundry.api.store.StoreModel
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.navigation.Screens
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MenuViewModel: ViewModel() {
    var menuListResponse: ArrayList<MenuModel> by mutableStateOf(arrayListOf())
    var stateMenu: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")
    var deleteAllMenu: Boolean by mutableStateOf(false)

    fun getMenu(){
        try {
            MenuApp.CreateInstance().fetchMenu(store = STORE_ID).enqueue(object :
                Callback<ArrayList<MenuModel>> {
                override fun onResponse(call: Call<ArrayList<MenuModel>>, response: Response<ArrayList<MenuModel>>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateMenu = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            menuListResponse = response.body()!!
//                            MACHINE_DATA = machineListResponse
//                            Log.d("debug", "Code : ${response.code().toString()}")
//                            Log.d("debug", "Code : ${menuListResponse}")
                            stateMenu = 1
                        }
                        if (menuListResponse.isNullOrEmpty()){
                            stateMenu = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<MenuModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateMenu = 2
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

    fun insertMenu(
        name: String,
        is_packet: Boolean,
        is_dryer: Boolean,
        is_service: Boolean,
        navController: NavController
    ){
        val bodyDataInsert = MenuModel(
            menuStore = STORE_ID,
            priceMenu = name,
            isPacket = is_packet,
            isDryer = is_dryer,
            isService = is_service
        )
        try {
            MenuApp.CreateInstance().insertMenu(statusData = bodyDataInsert).enqueue(object :
                Callback<MenuModel> {
                override fun onResponse(call: Call<MenuModel>, response: Response<MenuModel>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateMenu = 0
                    if(response.code() == 201){
                        navController.navigate(route = Screens.MenuPrice.route){
                            popUpTo(Screens.MenuPrice.route) {
                                inclusive = true
                            }
                        }
                        IS_DIALOG_OPEN.value = false
                    }
                    if(response.code() == 400){
                        stateMenu = 4
                    }
                }

                override fun onFailure(call: Call<MenuModel>, t: Throwable) {
                    Log.d("debug", "Fail Push Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateMenu = 2
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

    fun updateMenu(
        idMenu: String,
        titleMenu: String,
        is_packet: Boolean,
        is_dryer: Boolean,
        is_service: Boolean,
        navController: NavController
    ){
        val bodyDataUpdate = MenuModel(
            menuStore = STORE_ID,
            priceMenu = titleMenu,
            isPacket = is_packet,
            isDryer = is_dryer,
            isService = is_service
        )
//        val bodyUpdate = MachineModelUpdate(machineStatus = true, isPacket = isPacket, priceTime = timeMachine)

        try {
            MenuApp.CreateInstance().updateMenu(id = idMenu, bodyDataUpdate).enqueue(object : Callback<MenuModel> {
                override fun onResponse(call: Call<MenuModel>, response: Response<MenuModel>) {
//                    Log.d("debug", "Code Update ${response.code()}")
                    if(response.code() == 200){
                        navController.navigate(route = Screens.MenuPrice.route){
                            popUpTo(Screens.MenuPrice.route) {
                                inclusive = true
                            }
                        }
                        IS_DIALOG_OPEN.value = false
                    }
                }

                override fun onFailure(call: Call<MenuModel>, t: Throwable) {
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

    fun deleteMenu(idMenu: String, navController: NavController){
        try {
            MenuApp.CreateInstance().deleteMenu(id = idMenu).enqueue(object :
                Callback<MenuModel> {
                override fun onResponse(call: Call<MenuModel>, response: Response<MenuModel>) {
//                    Log.d("debug", "Code Delete Menu ${response.code()}")
                    if(response.code() == 200){
                        navController.navigate(route = Screens.MenuPrice.route){
                            popUpTo(Screens.MenuPrice.route) {
                                inclusive = true
                            }
                        }
                        IS_DIALOG_OPEN.value = false
                    }
                }

                override fun onFailure(call: Call<MenuModel>, t: Throwable) {
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

    fun deleteMenuInStore(idMenu: String){
        try {
            MenuApp.CreateInstance().deleteMenu(id = idMenu).enqueue(object :
                Callback<MenuModel> {
                override fun onResponse(call: Call<MenuModel>, response: Response<MenuModel>) {
//                    Log.d("debug", "Code Delete ${response.code()}")
                    if(response.code() != 200){
                        deleteMenuInStore(idMenu = idMenu)
                    }
                }

                override fun onFailure(call: Call<MenuModel>, t: Throwable) {
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

    fun getIDMenu(priceViewModel: PriceViewModel = PriceViewModel(), navController: NavController){
        try {
            MenuApp.CreateInstance().fetchIdMenu(lookup = "*", store = STORE_ID).enqueue(object :
                Callback<ArrayList<MenuModel>> {
                override fun onResponse(call: Call<ArrayList<MenuModel>>, response: Response<ArrayList<MenuModel>>) {
//                    Log.d("debug", "url : ${response}")
//                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateMenu = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            menuListResponse = response.body()!!
                            if (!menuListResponse.isNullOrEmpty()){
                                for (item in menuListResponse){
//                                    Log.d("debug", "Menu Response ID : ${item.id!!.toString()}")
                                    priceViewModel.getIDPriceRemoveAll(navController = navController, menuID = item.id!!.toString())
                                }
                                deleteAllMenu = true
                                moveDeleteStore(navController = navController)
                            }
                            else{
                                deleteAllMenu = true
                                moveDeleteStore(navController = navController)
                            }
//                            Log.d("debug", "Code : ${response.code().toString()}")
//                            Log.d("debug", "Price Response : ${priceListResponse.size}")
                            stateMenu = 1
                        }
                        if (menuListResponse.isNullOrEmpty()){
                            stateMenu = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<MenuModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateMenu = 2
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

    fun moveDeleteStore(storeViewModel: StoreViewModel = StoreViewModel(), navController: NavController){
        if(deleteAllMenu){
            Log.d("debug", "Delete Menu Success, then move to delete Store")
            storeViewModel.deleteStore(STORE_ID, navController = navController)
        }
    }
}