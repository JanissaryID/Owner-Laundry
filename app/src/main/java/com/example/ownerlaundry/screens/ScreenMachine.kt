package com.example.ownerlaundry.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.api.machine.MachineViewModel
import com.example.ownerlaundry.api.transaction.TransactionViewModel
import com.example.ownerlaundry.component.machine.MachineLoadData
import com.example.ownerlaundry.component.transaction.TransactionLoadData
import com.example.ownerlaundry.component.view.ViewDialogCalendar
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.component.view.ViewTopBarTransaction
import com.example.ownerlaundry.excel.ExcelViewModel
import com.example.ownerlaundry.navigation.Screens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenMachine(
    machineViewModel: MachineViewModel,
    navController: NavController,
) {
    val context = LocalContext.current
//    Log.d("debug", "Data Qris : $QRIS_DATA")

    Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = TITLE_SCREEN[8],
            screenBack = Screens.Menu.route
        ) },
        backgroundColor = MaterialTheme.colors.background,
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.padding(24.dp),
                onClick = {
                    MACHINE_ID = ""
                    MACHINE_CLASS = -1
                    MACHINE_TYPE = -1
                    MACHINE_NUMBER = 0
                    MACHINE_EDIT = false
                    navController.navigate(route = Screens.AddEditMachine.route)
//                    Toast.makeText(context, "Add Price Clicked", Toast.LENGTH_SHORT).show()
                }) {
                Icon(imageVector = Icons.Default.Add, "Add Price")
            }
        }
        ){
        WallMachine(
            navController = navController,
            machineViewModel = machineViewModel,
//            priceViewModel = priceViewModel,
//            machineViewModel = machineViewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallMachine(
    machineViewModel: MachineViewModel,
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    machineViewModel: MachineViewModel
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val stateMachine = machineViewModel.stateMachine
    val machine = machineViewModel.machineListResponse

    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        MachineLoadData(
            selectedIndex = selectedIndex,
            onItemClick = onItemClick,
            machine = machine,
            machineState = stateMachine,
            navController = navController
        )
    }
    if (IS_DIALOG_OPEN.value){
        ViewDialogCalendar()
    }
}