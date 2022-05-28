package com.example.ownerlaundry.screens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ownerlaundry.DATE_PICK
import com.example.ownerlaundry.KEY_URL
import com.example.ownerlaundry.PRICE_TITLE
import com.example.ownerlaundry.TITLE_SCREEN
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.component.ButtonView
import com.example.ownerlaundry.component.store.StoreLoadData
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.component.view.ViewTopBarHome
import com.example.ownerlaundry.navigation.Screens
import com.example.ownerlaundry.proto.ProtoViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenSetting(
    protoViewModel: ProtoViewModel,
    navController: NavController,
//    storeViewModel: StoreViewModel
//    componentActivity: ComponentActivity
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = TITLE_SCREEN[10],
            screenBack = Screens.Home.route
        ) },
        backgroundColor = MaterialTheme.colors.background
    ){
        WallSetting(
            protoViewModel = protoViewModel,
            navController = navController,
//            storeViewModel = storeViewModel
//            componentActivity = componentActivity
        )
    }
}

@Composable
fun WallSetting(
    protoViewModel: ProtoViewModel,
    navController: NavController,
//    storeViewModel: StoreViewModel
//    componentActivity: ComponentActivity
) {
    val context = LocalContext.current

    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    var button_enable by remember { mutableStateOf(false) }

    var text_key_setting by remember { mutableStateOf(TextFieldValue(KEY_URL)) }

    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Card(shape = RoundedCornerShape(20.dp)) {
            ConstraintLayout(modifier = Modifier
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            ) {

                val (inputField,button) = createRefs()

                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(inputField) {
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
                    value = text_key_setting,
                    label = { Text(text = "Key") },
                    onValueChange = {
                        text_key_setting = it
                    }
                )

                if(text_key_setting.text != ""){
                    button_enable = true
                }
                else{
                    button_enable = false
                }

                ButtonView(
                    title = "Save",
                    enable = button_enable,
                    modifier = Modifier.constrainAs(button){
                        top.linkTo(inputField.bottom, 10.dp)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                ) {
                    protoViewModel.updateValue(keyUrl = text_key_setting.text)
//                    storeViewModel.getStore()
                    Toast.makeText(context, "Save Key ${KEY_URL}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}