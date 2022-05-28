package com.example.ownerlaundry.proto

import android.app.Application
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ownerlaundry.DataPreferences
import com.example.ownerlaundry.KEY_URL
import com.example.ownerlaundry.api.machine.MachineModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProtoViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ProtoRepository(application)

//    val keyUrl = repository.readProto.asLiveData()
//    private lateinit var keyUrl: LiveData<DataPreferences>
    val keyUrl = repository.readProto.asLiveData()

//    fun getDataValue(componentActivity: ComponentActivity){
//        keyUrl.observe(componentActivity, {
//            KEY_URL = it.keyUrl
//            Log.d("debug", "Key From Proto $KEY_URL")
//        })
////        Log.d("debug", "Key From Proto ViewModel $keyUrl.")
////        KEY_URL = keyUrl.value.
//    }

    fun updateValue(keyUrl: String) = viewModelScope.launch(Dispatchers.IO){
        repository.updateValue(keyUrl = keyUrl)
    }
}