package com.example.ownerlaundry.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
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
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.component.ButtonView
import com.example.ownerlaundry.component.view.ViewDialogLoading
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.component.view.ViewTopBarEdit
import com.example.ownerlaundry.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenAddStore(
    storeViewModel: StoreViewModel,
    navController: NavController,
) {
    val context = LocalContext.current

    PAGE_SCREEN = "add_store_screen"

    Scaffold(
        topBar = { ViewTopBarEdit(
            navController = navController,
            title = if(STORE_EDIT) stringResource(R.string.Edit_Store) else stringResource(R.string.Add_Store),
            screenBack = Screens.Home.route,
        ) },
    ){
        WallAddStore(
            storeViewModel = storeViewModel,
            navController = navController,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallAddStore(
    storeViewModel: StoreViewModel,
    navController: NavController,
) {
    val dataName = listOf(
        stringResource(R.string.Store_Name),
        stringResource(R.string.Store_Address),
        stringResource(R.string.Store_City),
        stringResource(R.string.Store_Password)
    )
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var button_enable by remember { mutableStateOf(false) }

    var button_clicked by remember { mutableStateOf(false) }

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
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface
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
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface
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
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface
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
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface
            ),
            value = text_password,
            label = { Text(text = dataName[3]) },
            onValueChange = {
                text_password = it
            }
        )

        if(text_name.text != "" && text_address.text != "" && text_city.text != "" && text_password.text != "" && !button_clicked) button_enable = true else button_enable = false

        ButtonView(title = if(STORE_EDIT) stringResource(R.string.Save_Edit_Store) else stringResource(
                    R.string.Save_Store), modifier.constrainAs(buttonAddStore) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, button_enable){
            IS_DIALOG_OPEN.value = true
            if (STORE_EDIT){
                button_clicked = true
                button_enable = false
//                Toast.makeText(context, "Edit Save Machine", Toast.LENGTH_SHORT).show()
                storeViewModel.updateStore(
                    storeName = text_name.text,
                    storeAddress = text_address.text,
                    storeCity = text_city.text,
                    storePassword = text_password.text,
                    storeId = STORE_ID,
                    navController = navController
                )
//                storeViewModel.getStore()
            }
            else{
                button_clicked = true
                button_enable = false
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
            Toast.makeText(context, stringResource(R.string.Try_Again), Toast.LENGTH_SHORT).show()
        }
        if (IS_DIALOG_OPEN.value){
            ViewDialogLoading()
        }
    }
}