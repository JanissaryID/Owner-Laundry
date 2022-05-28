package com.example.ownerlaundry.proto

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import com.example.ownerlaundry.DataPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import java.io.IOException


class ProtoRepository(context: Context) {

//    var dataProto: DataPreferences = MutableStateFlow(listOf())

    private val datastore: DataStore<DataPreferences> = context.createDataStore(
        fileName = "myData",
        serializer = MySerializer()
    )

    val readProto: Flow<DataPreferences> = datastore.data
        .catch { exception ->
            if(exception is IOException){
                Log.d("debug", exception.message.toString())
                emit(DataPreferences.getDefaultInstance())
            }
            else{
                throw exception
            }
        }

//    val readProtoStateFlow: StateFlow<DataPreferences> get() = dataProto

    suspend fun updateValue(keyUrl: String){
        datastore.updateData { preference ->
            preference.toBuilder().setKeyUrl(keyUrl).build()
        }
    }
}