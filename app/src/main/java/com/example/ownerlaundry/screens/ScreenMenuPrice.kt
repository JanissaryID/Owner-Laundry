package com.example.ownerlaundry.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.api.menu.MenuViewModel
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.component.menu.MenuLoadData
import com.example.ownerlaundry.component.store.StoreLoadData
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.component.view.ViewTopBarHome
import com.example.ownerlaundry.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenMenuPrice(
    menuViewModel: MenuViewModel,
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    settingViewModel: SettingViewModel,
//    machineViewModel: MachineViewModel,
//    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current
//    Log.d("debug", "Data Qris : $QRIS_DATA")

    Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = TITLE_SCREEN[3],
            screenBack = Screens.Menu.route
        ) },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(24.dp),
                onClick = {
                    MENU_TITLE = ""
                    MENU_PACKET = false
                    MENU_DRYER = false
                    MENU_ID = ""
                    MENU_EDIT = false
                    navController.navigate(route = Screens.AddEditMenuPrice.route)
//                    Toast.makeText(context, "Add Store Clicked", Toast.LENGTH_SHORT).show()
                }) {
                Icon(imageVector = Icons.Default.Add, "Add Menu")
            }
        }
    ){
        WallMenuPrice(
            menuViewModel,
            navController = navController,
//            priceViewModel = priceViewModel,
//            machineViewModel = machineViewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallMenuPrice(
    menuViewModel: MenuViewModel,
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    machineViewModel: MachineViewModel
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val stateMenu = menuViewModel.stateMenu
    val menu = menuViewModel.menuListResponse

    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        MenuLoadData(
            selectedIndex = selectedIndex,
            onItemClick = onItemClick,
            menu = menu,
            menuState = stateMenu,
            navController = navController
        )
    }
}