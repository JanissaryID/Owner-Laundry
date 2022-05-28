package com.example.ownerlaundry.component.view

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
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
import com.example.ownerlaundry.excel.ExcelViewModel
import com.example.ownerlaundry.navigation.Screens

@Composable
fun ViewTopBarEdit(
    navController: NavController,
    title: String,
    screenBack: String,
    machineViewModel: MachineViewModel = MachineViewModel(),
    priceViewModel: PriceViewModel = PriceViewModel(),
    menuViewModel: MenuViewModel = MenuViewModel()
) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
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
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
//                    IS_DIALOG_OPEN.value = true
//                    navController.navigate(route = Screens.Setting.route)
                    if(PAGE_SCREEN == "add_menu_price_screen"){
                        if(!MENU_EDIT){
                            Toast.makeText(context, "Only Add Menu", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            menuViewModel.deleteMenu(idMenu = MENU_ID, navController = navController)
                        }
                    }
                    else if(PAGE_SCREEN == "add_price_screen"){
                        if(!PRICE_EDIT){
                            Toast.makeText(context, "Only Add Price", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            priceViewModel.deletePrice(idPrice = PRICE_ID, navController = navController)
                        }
                    }
                    else if(PAGE_SCREEN == "add_machine_screen"){
                        if(!MACHINE_EDIT){
                            Toast.makeText(context, "Only Add Machine", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            machineViewModel.deleteMachine(idMachine = MACHINE_ID, navController = navController)
                        }
                    }
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_delete_24), contentDescription = "Delete", tint = MaterialTheme.colors.primary)
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
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
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
                    tint = MaterialTheme.colors.primary
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
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
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
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(route = Screens.AddStore.route)
//                    Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_edit_24), contentDescription = "Edit Store", tint = MaterialTheme.colors.primary)
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

    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(route = Screens.Setting.route)
//                    Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
                }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu", tint = MaterialTheme.colors.primary)
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
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
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
                    tint = MaterialTheme.colors.primary
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
                Icon(painter = painterResource(id = R.drawable.ic_baseline_calendar_today_24), contentDescription = "Calendar", tint = MaterialTheme.colors.primary)
            }
            IconButton(
                onClick = {
//                    navController.navigate(route = Screens.Setting.route)
//                    Log.d("debug", "Data : $EXCEL_VALUE")
                    Toast.makeText(context, "Export", Toast.LENGTH_SHORT).show()
                    excelViewModel.createExcelSheet(DATE_PICK)
                    if (excelViewModel.stateExcel == 1){
                        Toast.makeText(context, "Success Export", Toast.LENGTH_SHORT).show()
                    }
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_input_24), contentDescription = "Export", tint = MaterialTheme.colors.primary)
            }
        }
    )
}