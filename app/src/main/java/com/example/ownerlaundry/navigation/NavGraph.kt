package com.example.ownerlaundry.navigation

import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ownerlaundry.*
import com.example.ownerlaundry.api.debug.DebugViewModel
import com.example.ownerlaundry.api.machine.MachineViewModel
import com.example.ownerlaundry.api.menu.MenuViewModel
import com.example.ownerlaundry.api.price.PriceViewModel
import com.example.ownerlaundry.api.qris.QrisModel
import com.example.ownerlaundry.api.qris.QrisViewModel
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.api.transaction.TransactionViewModel
import com.example.ownerlaundry.excel.ExcelViewModel
import com.example.ownerlaundry.proto.ProtoViewModel
import com.example.ownerlaundry.screens.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    storeViewModel: StoreViewModel,
    qrisViewModel: QrisViewModel,
    menuViewModel: MenuViewModel,
    priceViewModel: PriceViewModel,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel,
    protoViewModel: ProtoViewModel,
    excelViewModel: ExcelViewModel,
    debugViewModel: DebugViewModel,
    componentActivity: ComponentActivity
) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Screens.Home.route){

        composable(
            route = Screens.Home.route,
        ){
            if (!KEY_URL.isNullOrEmpty()){
                storeViewModel.getStore()
            }
            else{
                protoViewModel.keyUrl.observe(componentActivity, {
                    KEY_URL = it.keyUrl
                    Log.d("debug", "Key From Proto Nav $KEY_URL")
                })
            }
            ScreenHome(
                storeViewModel = storeViewModel,
                navController = navController
//                priceViewModel = priceViewModel,
//                settingViewModel = settingViewModel,
//                transactionViewModel = transactionViewModel,
//                machineViewModel = machineViewModel
            )
        }

        composable(
            route = Screens.AddStore.route,
        ){
            ScreenAddStore(navController = navController, storeViewModel = storeViewModel)
        }

        composable(
            route = Screens.Menu.route,
        ){
            ScreenMenu(
                navController = navController,
//                machineViewModel = machineViewModel,
//                transactionViewModel = transactionViewModel
            )
        }

        composable(
            route = Screens.Qris.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                qrisViewModel.qrisListResponse.clear()
                QRIS_DATA.clear()
                qrisViewModel.getQris(navController = navController, deleteAll = false)
//                Log.d("debug", "Store ID : ${STORE_ID}")
            }
            ScreenQris(
                navController = navController,
                qrisViewModel = qrisViewModel,
//                machineViewModel = machineViewModel,
//                transactionViewModel = transactionViewModel
            )
        }

        composable(
            route = Screens.MenuPrice.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                menuViewModel.getMenu()
//                Log.d("debug", "Store ID : ${STORE_ID}")
            }
            ScreenMenuPrice(
                navController = navController,
                menuViewModel = menuViewModel
//                qrisViewModel = qrisViewModel,
//                machineViewModel = machineViewModel,
//                transactionViewModel = transactionViewModel
            )
        }

        composable(
            route = Screens.AddEditMenuPrice.route,
        ){
            ScreenMenuPriceAddEdit(
                navController = navController,
                menuViewModel = menuViewModel
//                qrisViewModel = qrisViewModel,
//                machineViewModel = machineViewModel,
//                transactionViewModel = transactionViewModel
            )
        }

        composable(
            route = Screens.Price.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                priceViewModel.getPrice()
//                Log.d("debug", "Store ID : ${STORE_ID}")
            }
            ScreenPrice(
                navController = navController,
                priceViewModel = priceViewModel
//                qrisViewModel = qrisViewModel,
//                machineViewModel = machineViewModel,
//                transactionViewModel = transactionViewModel
            )
        }

        composable(
            route = Screens.AddEditPrice.route,
        ){
            menuViewModel.getMenu()
            ScreenPriceAddEdit(
                navController = navController,
                menuViewModel = menuViewModel,
                priceViewModel = priceViewModel
//                qrisViewModel = qrisViewModel,
//                machineViewModel = machineViewModel,
//                transactionViewModel = transactionViewModel
            )
        }

        composable(
            route = Screens.ListTransactions.route,
        ){
            if (DATE_PICK != ""){
                transactionViewModel.getTransaction()
                debugViewModel.getDebug()
            }
            transactionViewModel.getTransaction()
            debugViewModel.getDebug()
            ScreenTransaction(
                navController = navController,
                transactionViewModel = transactionViewModel,
                excelViewModel = excelViewModel
//                priceViewModel = priceViewModel
            )
        }

        composable(
            route = Screens.Machine.route,
        ){
            LaunchedEffect(key1 = STORE_ID){
                machineViewModel.getMachine()
//                Log.d("debug", "Store ID : ${STORE_ID}")
            }
            ScreenMachine(
                navController = navController,
                machineViewModel = machineViewModel
//                qrisViewModel = qrisViewModel,
//                machineViewModel = machineViewModel,
//                transactionViewModel = transactionViewModel
            )
        }

        composable(
            route = Screens.AddEditMachine.route,
        ){
//            LaunchedEffect(key1 = STORE_ID){
//                machineViewModel.getMachine()
////                Log.d("debug", "Store ID : ${STORE_ID}")
//            }
            ScreenMachineAddEdit(
                navController = navController,
                machineViewModel = machineViewModel
//                qrisViewModel = qrisViewModel,
//                machineViewModel = machineViewModel,
//                transactionViewModel = transactionViewModel
            )
        }

        composable(
            route = Screens.Setting.route,
        ){
            ScreenSetting(
                protoViewModel = protoViewModel,
                navController = navController,
//                storeViewModel = storeViewModel
            )
        }

    }
}