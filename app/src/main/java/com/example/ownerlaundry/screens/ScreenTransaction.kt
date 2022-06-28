package com.example.ownerlaundry.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.ownerlaundry.R
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.api.transaction.TransactionViewModel
import com.example.ownerlaundry.component.transaction.TransactionLoadData
import com.example.ownerlaundry.component.view.ViewDialogCalendar
import com.example.ownerlaundry.component.view.ViewTopBarTransaction
import com.example.ownerlaundry.excel.ExcelViewModel
import com.example.ownerlaundry.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenTransaction(
    transactionViewModel: TransactionViewModel,
    navController: NavController,
    excelViewModel: ExcelViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBarTransaction(
            excelViewModel = excelViewModel,
            navController = navController,
            title = stringResource(R.string.Transaction),
            screenBack = Screens.Menu.route
        ) },

    ){
        WallTransaction(
            navController = navController,
            transactionViewModel = transactionViewModel,
            excelViewModel = excelViewModel
//            priceViewModel = priceViewModel,
//            machineViewModel = machineViewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallTransaction(
    transactionViewModel: TransactionViewModel,
    navController: NavController,
    excelViewModel: ExcelViewModel
//    priceViewModel: PriceViewModel,
//    machineViewModel: MachineViewModel
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val stateTranscation = transactionViewModel.stateTransaction
    val transaction = transactionViewModel.transactionListResponse

    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        TransactionLoadData(
            selectedIndex = selectedIndex,
            onItemClick = onItemClick,
            transaction = transaction,
            transactionState = stateTranscation,
            navController = navController
        )
    }
    if (IS_DIALOG_OPEN.value){
        ViewDialogCalendar()
    }
}