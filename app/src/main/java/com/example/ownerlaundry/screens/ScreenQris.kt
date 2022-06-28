package com.example.ownerlaundry.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.R
import com.example.ownerlaundry.api.qris.QrisViewModel
import com.example.ownerlaundry.component.ButtonView
import com.example.ownerlaundry.component.view.ViewDialogLoading
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.component.view.ViewTopBarEdit
import com.example.ownerlaundry.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenQris(
    qrisViewModel: QrisViewModel,
    navController: NavController,
) {
    val context = LocalContext.current

    PAGE_SCREEN = "qris_screen"

    Scaffold(
        topBar = { ViewTopBarEdit(
            navController = navController,
            title = TITLE_SCREEN[2],
            screenBack = Screens.Menu.route
        ) },
    ){
        WallAddQris(
            qrisViewModel = qrisViewModel,
            navController = navController,
        )
    }
}

@Composable
fun WallAddQris(
    qrisViewModel: QrisViewModel,
    navController: NavController,
) {
    val dataName = listOf(
        "Client Key",
        "Client ID",
        "Merchant ID",
    )
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var button_enable by remember { mutableStateOf(false) }
    var button_clicked by remember { mutableStateOf(false) }

    var text_client_key by remember { mutableStateOf(TextFieldValue("")) }
    var text_client_id by remember { mutableStateOf(TextFieldValue("")) }
    var text_merchant_id by remember { mutableStateOf(TextFieldValue("")) }
//    var idQris by remember { mutableStateOf("") }

    if (!QRIS_DATA.isNullOrEmpty()){
        text_client_key = TextFieldValue(QRIS_DATA[0].clientKey.toString())
        text_client_id = TextFieldValue(QRIS_DATA[0].clientId.toString())
        text_merchant_id = TextFieldValue(QRIS_DATA[0].merchantId.toString())
        QRIS_ID = QRIS_DATA[0].id.toString()
//        idQris = QRIS_DATA[0].id.toString()
    }

    ConstraintLayout(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        .fillMaxSize()
    ) {

        val (TextName, TextAddress, TextCity, buttonAddStore) = createRefs()
        val modifier = Modifier

        OutlinedTextField(
            modifier = modifier.fillMaxWidth().constrainAs(TextName) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface
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
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface
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
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface
            ),
            value = text_merchant_id,
            label = { Text(text = dataName[2]) },
            onValueChange = {
                text_merchant_id = it
            }
        )

        if(text_client_key.text != "" && text_client_id.text != "" && text_merchant_id.text != "" && !button_clicked){
            button_enable = true
        }
        else{
            button_enable = false
        }

        ButtonView(title = stringResource(R.string.Save_Qris), modifier.constrainAs(buttonAddStore) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, button_enable){
            IS_DIALOG_OPEN.value = true
            button_clicked = true
            button_enable = false
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
                button_clicked = true
                button_enable = false
                qrisViewModel.updateQris(
                    clientkey = text_client_key.text,
                    clientID = text_client_id.text,
                    merchantID = text_merchant_id.text,
                    idQris = QRIS_ID,
                    navController = navController
                )
//                Toast.makeText(context, "Data Found", Toast.LENGTH_SHORT).show()
            }
        }
        if(qrisViewModel.stateQris == 4){
            Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
        }
        if (IS_DIALOG_OPEN.value){
            ViewDialogLoading()
        }
    }
}