package com.example.ownerlaundry

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ownerlaundry.api.debug.DebugViewModel
import com.example.ownerlaundry.api.machine.MachineViewModel
import com.example.ownerlaundry.api.menu.MenuViewModel
import com.example.ownerlaundry.api.price.PriceViewModel
import com.example.ownerlaundry.api.qris.QrisViewModel
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.api.transaction.TransactionViewModel
import com.example.ownerlaundry.excel.ExcelViewModel
import com.example.ownerlaundry.navigation.NavGraphSetup
import com.example.ownerlaundry.proto.ProtoViewModel
import com.example.ownerlaundry.ui.theme.OwnerLaundryTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    val storeViewModel by viewModels<StoreViewModel>()
    val qrisViewModel by viewModels<QrisViewModel>()
    val menuViewModel by viewModels<MenuViewModel>()
    val priceViewModel by viewModels<PriceViewModel>()
    val transactionViewModel by viewModels<TransactionViewModel>()
    val excelViewModel by viewModels<ExcelViewModel>()
    val machineViewModel by viewModels<MachineViewModel>()
    val debugViewModel by viewModels<DebugViewModel>()

    private lateinit var protoViewModel: ProtoViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        protoViewModel = ViewModelProvider(this).get(ProtoViewModel::class.java)
//        protoViewModel.getDataValue(componentActivity = this)
//        Log.d("debug", "Key From Proto $KEY_URL")
        protoViewModel.keyUrl.observe(this, {
            KEY_URL = it.keyUrl
            Log.d("debug", "Key From Proto $KEY_URL")
        })

        setContent {
            OwnerLaundryTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                NavGraphSetup(
                    navController = navController,
                    storeViewModel = storeViewModel,
                    qrisViewModel = qrisViewModel,
                    menuViewModel = menuViewModel,
                    priceViewModel = priceViewModel,
                    transactionViewModel = transactionViewModel,
                    machineViewModel = machineViewModel,
                    excelViewModel = excelViewModel,
                    protoViewModel = protoViewModel,
                    debugViewModel = debugViewModel,
                    componentActivity = this
                )
            }
        }
    }
}