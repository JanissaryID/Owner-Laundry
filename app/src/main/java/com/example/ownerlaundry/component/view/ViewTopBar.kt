package com.example.ownerlaundry.component.view

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.R
import com.example.ownerlaundry.api.machine.MachineViewModel
import com.example.ownerlaundry.api.menu.MenuViewModel
import com.example.ownerlaundry.api.price.PriceViewModel
import com.example.ownerlaundry.api.qris.QrisViewModel
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.excel.ExcelViewModel
import com.example.ownerlaundry.navigation.Screens

@Composable
fun ViewTopBarEdit(
    navController: NavController,
    title: String,
    screenBack: String,
    machineViewModel: MachineViewModel = MachineViewModel(),
    priceViewModel: PriceViewModel = PriceViewModel(),
    menuViewModel: MenuViewModel = MenuViewModel(),
    qrisViewModel: QrisViewModel = QrisViewModel(),
    storeViewModel: StoreViewModel = StoreViewModel()
) {

    val context = LocalContext.current

    SmallTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(route = screenBack) {
                        if (screenBack == "menu_screen"){
                            QRIS_DATA.clear()
                            Log.d("debug", "Data Qris : ${QRIS_DATA}")
                        }
//                        else if (screenBack == "home_screen"){
//                            MENU_VALUE = ""
//                            MENU_VALUE_MACHINE = ""
//                            INDEX_CLASS_MACHINE = -1
//                            PAYMENT_SUCCESS = true
//                        }
                        popUpTo(screenBack) {
                            inclusive = true
                        }
                    }
//                        Toast.makeText(context, "Screen $screenBack", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    when(PAGE_SCREEN){
                        "add_menu_price_screen" -> {
                            if(!MENU_EDIT){
                                Toast.makeText(context, "Only Add Menu", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                IS_DIALOG_OPEN.value = true
                                priceViewModel.getIDPrice(navController = navController)
//                            menuViewModel.deleteMenu(idMenu = MENU_ID, navController = navController)
                            }
                        }
                        "add_price_screen" -> {
                            if(!PRICE_EDIT){
                                Toast.makeText(context, "Only Add Price", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                IS_DIALOG_OPEN.value = true
                                priceViewModel.deletePrice(idPrice = PRICE_ID, navController = navController)
                            }
                        }
                        "add_machine_screen" -> {
                            if(!MACHINE_EDIT){
                                Toast.makeText(context, "Only Add Machine", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                IS_DIALOG_OPEN.value = true
                                machineViewModel.deleteMachine(idMachine = MACHINE_ID, navController = navController)
                            }
                        }
                        "qris_screen" -> {
                            if(!QRIS_EDIT){
                                Toast.makeText(context, "Only Add Qris", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                IS_DIALOG_OPEN.value = true
                                qrisViewModel.deleteQris(idQris = QRIS_ID, navController = navController, deleteAll = false)
                            }
                        }
                        "add_store_screen" -> {
                            if(!STORE_EDIT){
                                Toast.makeText(context, "Only Add Store", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                IS_DIALOG_OPEN.value = true
                                machineViewModel.getIDMachine(navController = navController)
                            }
                        }
                    }
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_delete_24), contentDescription = "Delete", tint = MaterialTheme.colorScheme.primary)
            }
        }
    )
}

@Composable
fun ViewTopBar(
    navController: NavController,
    title: String,
//    qrisViewModel: QrisViewModel,
    screenBack: String
) {
    val context = LocalContext.current
    SmallTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(route = screenBack) {
                        popUpTo(screenBack) {
                            inclusive = true
                        }
                    }
//                        Toast.makeText(context, "Screen $screenBack", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@Composable
fun ViewTopBarMenu(
    navController: NavController,
    title: String,
//    qrisViewModel: QrisViewModel,
    screenBack: String
) {
    val context = LocalContext.current
    SmallTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(route = screenBack) {
                        popUpTo(screenBack) {
                            inclusive = true
                        }
                    }
                        Toast.makeText(context, "Screen $screenBack", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(route = Screens.AddStore.route)
//                    Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_edit_24), contentDescription = "Edit Store", tint = MaterialTheme.colorScheme.primary)
            }
        }
    )
}

@Composable
fun ViewTopBarHome(
    navController: NavController,
    title: String,
) {
    val context = LocalContext.current

    SmallTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(route = Screens.Setting.route)
//                    Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
                }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu", tint = MaterialTheme.colorScheme.primary)
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ViewTopBarTransaction(
    navController: NavController,
    excelViewModel: ExcelViewModel,
    title: String,
//    qrisViewModel: QrisViewModel,
    screenBack: String
) {
    val context = LocalContext.current
    var button_clicked by remember { mutableStateOf(true) }

    SmallTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(route = screenBack) {
                        if (screenBack == "menu_screen"){
                            QRIS_DATA.clear()
                            Log.d("debug", "Data Qris : ${QRIS_DATA}")
                        }
//                        else if (screenBack == "home_screen"){
//                            MENU_VALUE = ""
//                            MENU_VALUE_MACHINE = ""
//                            INDEX_CLASS_MACHINE = -1
//                            PAYMENT_SUCCESS = true
//                        }
                        popUpTo(screenBack) {
                            inclusive = true
                        }
                    }
//                        Toast.makeText(context, "Screen $screenBack", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    IS_DIALOG_OPEN.value = true
//                    navController.navigate(route = Screens.Setting.route)
//                    Toast.makeText(context, "Calendar", Toast.LENGTH_SHORT).show()
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_calendar_today_24), contentDescription = "Calendar", tint = MaterialTheme.colorScheme.primary)
            }
            IconButton(
                enabled = button_clicked,
                onClick = {
//                    navController.navigate(route = Screens.Setting.route)
//                    Log.d("debug", "Data : $EXCEL_VALUE")
                    button_clicked = false
                    Toast.makeText(context, "Export", Toast.LENGTH_SHORT).show()
                    excelViewModel.createExcelSheet(DATE_PICK)
                    if (excelViewModel.stateExcel == 1){
                        Toast.makeText(context, "Success Export", Toast.LENGTH_SHORT).show()
                    }
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_input_24), contentDescription = "Export", tint = MaterialTheme.colorScheme.primary)
            }
        }
    )
}