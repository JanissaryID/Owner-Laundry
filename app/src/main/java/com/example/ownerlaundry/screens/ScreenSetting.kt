package com.example.ownerlaundry.screens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ownerlaundry.KEY_URL
import com.example.ownerlaundry.TITLE_SCREEN
import com.example.ownerlaundry.component.ButtonView
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.navigation.Screens
import com.example.ownerlaundry.proto.ProtoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenSetting(
    protoViewModel: ProtoViewModel,
    navController: NavController,
) {
    val context = LocalContext.current

   Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = TITLE_SCREEN[10],
            screenBack = Screens.Home.route
        ) },
    ){
        WallSetting(
            protoViewModel = protoViewModel,
            navController = navController,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallSetting(
    protoViewModel: ProtoViewModel,
    navController: NavController,
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
                        focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                        textColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.onSurface
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
                    Toast.makeText(context, "Save Key ${KEY_URL}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}