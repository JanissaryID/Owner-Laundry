package com.example.ownerlaundry.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.component.ButtonView
import com.example.ownerlaundry.component.store.StoreLoadData
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.navigation.Screens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenAddStore(
    storeViewModel: StoreViewModel,
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    settingViewModel: SettingViewModel,
//    machineViewModel: MachineViewModel,
//    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = TITLE_SCREEN[1],
            screenBack = Screens.Home.route,
        ) },
        backgroundColor = MaterialTheme.colors.background
    ){
        WallAddStore(
            storeViewModel = storeViewModel,
            navController = navController,
//            priceViewModel = priceViewModel,
//            machineViewModel = machineViewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallAddStore(
    storeViewModel: StoreViewModel,
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    machineViewModel: MachineViewModel
) {
    val dataName = listOf(
        "Store Name",
        "Store Address",
        "Store City",
        "Store Password"
    )
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var button_enable by remember { mutableStateOf(false) }

    var text_name by remember { mutableStateOf(TextFieldValue(STORE_NAME)) }
    var text_address by remember { mutableStateOf(TextFieldValue(STORE_ADDRESS)) }
    var text_city by remember { mutableStateOf(TextFieldValue(STORE_CITY)) }
    var text_password by remember { mutableStateOf(TextFieldValue(STORE_PASSWORD)) }

    ConstraintLayout(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        .fillMaxSize()
    ) {
        val (TextName, TextAddress, TextCity, TextPassword, buttonAddStore) = createRefs()
        val modifier = Modifier

        OutlinedTextField(
            modifier = modifier.fillMaxWidth().constrainAs(TextName) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.onSurface,
                focusedLabelColor = MaterialTheme.colors.onSurface,
                textColor = MaterialTheme.colors.onSurface,
                cursorColor = MaterialTheme.colors.onSurface
            ),
            value = text_name,
            label = { Text(text = dataName[0]) },
            onValueChange = {
                text_name = it
            }
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth().constrainAs(TextAddress) {
                top.linkTo(TextName.bottom, 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.onSurface,
                focusedLabelColor = MaterialTheme.colors.onSurface,
                textColor = MaterialTheme.colors.onSurface,
                cursorColor = MaterialTheme.colors.onSurface
            ),
            value = text_address,
            label = { Text(text = dataName[1]) },
            onValueChange = {
                text_address = it
            }
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth().constrainAs(TextCity) {
                top.linkTo(TextAddress.bottom, 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.onSurface,
                focusedLabelColor = MaterialTheme.colors.onSurface,
                textColor = MaterialTheme.colors.onSurface,
                cursorColor = MaterialTheme.colors.onSurface
            ),
            value = text_city,
            label = { Text(text = dataName[2]) },
            onValueChange = {
                text_city = it
            }
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth().constrainAs(TextPassword) {
                top.linkTo(TextCity.bottom, 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.onSurface,
                focusedLabelColor = MaterialTheme.colors.onSurface,
                textColor = MaterialTheme.colors.onSurface,
                cursorColor = MaterialTheme.colors.onSurface
            ),
            value = text_password,
            label = { Text(text = dataName[3]) },
            onValueChange = {
                text_password = it
            }
        )

        if(text_name.text != "" && text_address.text != "" && text_city.text != "" && text_password.text != ""){
            button_enable = true
        }
        else{
            button_enable = false
        }

        ButtonView(title = if(STORE_EDIT) "Save Edit Store" else "Save Store", modifier.constrainAs(buttonAddStore) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, button_enable){
            if (STORE_EDIT){
//                Toast.makeText(context, "Edit Save Machine", Toast.LENGTH_SHORT).show()
                storeViewModel.updateStore(
                    storeName = text_name.text,
                    storeAddress = text_address.text,
                    storeCity = text_city.text,
                    storePassword = text_password.text,
                    storeId = STORE_ID,
                    navController = navController
                )

            }
            else{
//                Toast.makeText(context, "Save Machine", Toast.LENGTH_SHORT).show()
                storeViewModel.insertStore(
                    name = text_name.text,
                    address = text_address.text,
                    city = text_city.text,
                    password = text_password.text,
                    navController = navController
                )
            }

        }
        if(storeViewModel.stateStore == 4){
            Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
        }
    }
}