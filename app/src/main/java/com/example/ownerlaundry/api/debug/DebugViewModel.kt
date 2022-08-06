package com.example.ownerlaundry.api.debug

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ownerlaundry.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class DebugViewModel: ViewModel() {

    var debugListResponse: ArrayList<DebugModel> by mutableStateOf(arrayListOf())
    var stateDebug: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getDebug(){
        try {
            Log.d("debug", "Date Debug : $DATE_PICK")
            DebugApp.CreateInstance().fetchDebug(lookup = "*", date = "$DATE_PICK").enqueue(object :
                Callback<ArrayList<DebugModel>> {
                override fun onResponse(call: Call<ArrayList<DebugModel>>, response: Response<ArrayList<DebugModel>>) {
//                    Log.d("debug", "url : ${response}")
                    Log.d("debug", "Code : ${response.code()}")
                    stateDebug = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            debugListResponse = response.body()!!
                            EXCEL_VALUE_DEBUG = debugListResponse

//                            Log.d("debug", "Code : ${response.code().toString()}")
                            Log.d("debug", "Transaction Data : ${debugListResponse}")
                            stateDebug = 1
                        }
                        if (debugListResponse.isNullOrEmpty()){
                            stateDebug = 3
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<DebugModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateDebug = 2
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

    fun addListTransactionDebug(){
        val list_washer: ArrayList<DebugTransactionModel> = ArrayList()
        val list_dryer: ArrayList<DebugTransactionModel> = ArrayList()
        val list_all: ArrayList<DebugTransactionModel> = ArrayList()
        val row_wahser: ArrayList<Int> = ArrayList()
        val row_dryer: ArrayList<Int> = ArrayList()

        var indexWasher = 0
        var indexDryer = 0

        val rowMachine: ArrayList<Int> = ArrayList()
        rowMachine.clear()
        rowMachine.clear()

//        val countMachine: ArrayList<Int> = ArrayList()

        EXCEL_VALUE_DEBUG.forEach {
            val number = it.responseBody!!.split(",")

            val data = DebugTransactionModel(
                date = it.date,
                time = it.time,
                NumberMachine = number[3].substring(15).toInt(),
                TypeMenu = number[0].substring(25).toBoolean(),
                isPacket = number[1].substring(10).toBoolean()
            )
            list_all.add(data)
            if(data.TypeMenu == false){
                list_washer.add(data)
                if (data.isPacket == false){
                    row_dryer.add(indexDryer)
                    rowMachine.add(indexDryer)
                }
                indexDryer++
            }
            else{
                list_dryer.add(data)
                if (data.isPacket == false){
                    row_wahser.add(indexWasher)
                    rowMachine.add(indexWasher)
                }
                indexWasher++
            }

            if (data.NumberMachine == 1){
                WASHER_COUNT_TITAN++
            }
            else if(data.NumberMachine == 2){
                DRYER_COUNT_TITAN++
            }
            else if((data.NumberMachine!! % 2) == 0){
                DRYER_COUNT_GIANT++
            }
            else if((data.NumberMachine!! % 2) != 0){
                WASHER_COUNT_GIANT++
            }
        }

        EXCEL_VALUE_DEBUG_WASHER = list_washer
        EXCEL_VALUE_DEBUG_DRYER = list_dryer
        EXCEL_VALUE_DEBUG_ALL = list_all

        ROW_WAHSER_EMPTY = row_wahser
        ROW_DRYER_EMPTY = row_dryer
        ROW_MACHINE_PLUS_ONE = rowMachine

        Log.d("debug", "Machine plus 1 : ${ROW_MACHINE_PLUS_ONE}")

        Log.d("debug", "Washer Row Empty : ${ROW_WAHSER_EMPTY}")
        Log.d("debug", "Dryer Row Empty : ${ROW_DRYER_EMPTY}")
//        Log.d("debug", "Dryer Row Empty : ${EXCEL_VALUE_DEBUG_ALL}")
//
        Log.d("debug", "Washer Row Length : ${ROW_WAHSER_EMPTY.size}")
        Log.d("debug", "Dryer Row Length : ${ROW_DRYER_EMPTY.size}")
//        Log.d("debug", "Dryer Row Length : ${ROW_DRYER_EMPTY.size}")
    }
}

