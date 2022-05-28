package com.example.ownerlaundry.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ownerlaundry.QRIS_DATA
//import com.example.ownerlaundry.QRIS_DATA
import com.example.ownerlaundry.TITLE_SCREEN
import com.example.ownerlaundry.api.qris.QrisViewModel
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.component.ButtonView
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.navigation.Screens

@Composable
fun ScreenQris(
    qrisViewModel: QrisViewModel,
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    settingViewModel: SettingViewModel,
//    machineViewModel: MachineViewModel,
//    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current

//    BackHandler(enabled = false) {
//        //do nothing
//        QRIS_DATA.clear()
////        Log.d("debug", "Data Qris : ${QRIS_DATA}")
//    }

    Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = TITLE_SCREEN[2],
            screenBack = Screens.Menu.route
        ) },
        backgroundColor = MaterialTheme.colors.background
    ){
        WallAddQris(
            qrisViewModel = qrisViewModel,
            navController = navController,
//            priceViewModel = priceViewModel,
//            machineViewModel = machineViewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallAddQris(
    qrisViewModel: QrisViewModel,
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    machineViewModel: MachineViewModel
) {
    val dataName = listOf(
        "Client Key",
        "Client ID",
        "Merchant ID",
    )
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var button_enable by remember { mutableStateOf(false) }

    var text_client_key by remember { mutableStateOf(TextFieldValue("")) }
    var text_client_id by remember { mutableStateOf(TextFieldValue("")) }
    var text_merchant_id by remember { mutableStateOf(TextFieldValue("")) }
    var idQris by remember { mutableStateOf("") }

    if (!QRIS_DATA.isNullOrEmpty()){
        text_client_key = TextFieldValue(QRIS_DATA[0].clientKey.toString())
        text_client_id = TextFieldValue(QRIS_DATA[0].clientId.toString())
        text_merchant_id = TextFieldValue(QRIS_DATA[0].merchantId.toString())
        idQris = QRIS_DATA[0].id.toString()
    }

    ConstraintLayout(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        .fillMaxSize()
    ) {

//        val content = createRef()
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
            value = text_client_key,
            label = { Text(text = dataName[0]) },
            onValueChange = {
                text_client_key = it
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
            value = text_client_id,
            label = { Text(text = dataName[1]) },
            onValueChange = {
                text_client_id = it
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
            value = text_merchant_id,
            label = { Text(text = dataName[2]) },
            onValueChange = {
                text_merchant_id = it
            }
        )

        if(text_client_key.text != "" && text_client_id.text != "" && text_merchant_id.text != ""){
            button_enable = true
        }
        else{
            button_enable = false
        }

        ButtonView(title = "Save Qris", modifier.constrainAs(buttonAddStore) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, button_enable){
            if (QRIS_DATA.isNullOrEmpty()){
                qrisViewModel.insertQris(
                    clientkey = text_client_key.text,
                    clientID = text_client_id.text,
                    merchantID = text_merchant_id.text,
                    navController = navController
                )
//                Toast.makeText(context, "Empty Data", Toast.LENGTH_SHORT).show()
            }
            else{
                qrisViewModel.updateQris(
                    clientkey = text_client_key.text,
                    clientID = text_client_id.text,
                    merchantID = text_merchant_id.text,
                    idQris = idQris,
                    navController = navController
                )
//                Toast.makeText(context, "Data Found", Toast.LENGTH_SHORT).show()
            }

//            storeViewModel.insertStore(
//                name = text_name.text,
//                address = text_address.text,
//                city = text_city.text,
//                password = text_password.text,
//                navController = navController
//            )
        }
        if(qrisViewModel.stateQris == 4){
            Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
        }
    }
}