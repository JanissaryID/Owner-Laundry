package com.example.ownerlaundry.screens

import android.util.Log
import android.widget.Toast
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
import com.example.ownerlaundry.*
import com.example.ownerlaundry.api.menu.MenuViewModel
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.component.ButtonRadio
import com.example.ownerlaundry.component.ButtonView
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.component.view.ViewTopBarEdit
import com.example.ownerlaundry.navigation.Screens

@Composable
fun ScreenMenuPriceAddEdit(
    menuViewModel: MenuViewModel,
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    settingViewModel: SettingViewModel,
//    machineViewModel: MachineViewModel,
//    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBarEdit(
            navController = navController,
            title = if (MENU_EDIT) "Edit Menu Price" else TITLE_SCREEN[4],
            screenBack = Screens.MenuPrice.route,
        ) },
        backgroundColor = MaterialTheme.colors.background
    ){
        WallMenuPriceAddEdit(
            menuViewModel = menuViewModel,
            navController = navController,
//            priceViewModel = priceViewModel,
//            machineViewModel = machineViewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallMenuPriceAddEdit(
    menuViewModel: MenuViewModel,
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    machineViewModel: MachineViewModel
) {
    val dataName = listOf(
        "Name Menu",
        "Packet",
        "Dryer",
    )
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var button_enable by remember { mutableStateOf(false) }

    var text_name_menu by remember { mutableStateOf(TextFieldValue(MENU_TITLE)) }
    var button_packet_menu by remember { mutableStateOf(MENU_PACKET) }
    var button_dryer_menu by remember { mutableStateOf(MENU_DRYER) }

//    Toast.makeText(context, "Menu Edit $MENU_EDIT $MENU_ID", Toast.LENGTH_SHORT).show()

    ConstraintLayout(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        .fillMaxSize()
    ) {

//        val content = createRef()
        val (TextName, buttonPacket, buttonDryer, buttonAddMenu) = createRefs()
        val modifier = Modifier

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .constrainAs(TextName) {
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
            value = text_name_menu,
            label = { Text(text = dataName[0]) },
            onValueChange = {
                text_name_menu = it
            }
        )
        
        ButtonRadio(
            stateButton = button_packet_menu,
            title = "Packet",
            color = MaterialTheme.colors.surface,
            modifier = modifier.constrainAs(buttonPacket) {
                top.linkTo(TextName.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ){
            button_packet_menu = it
//            Log.d("debug", "packet $button_packet_menu")
        }

        ButtonRadio(
            stateButton = button_dryer_menu,
            title = "Dryer",
            color = MaterialTheme.colors.surface,
            modifier = modifier.constrainAs(buttonDryer) {
                top.linkTo(buttonPacket.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ){
            button_dryer_menu = it
//            Log.d("debug", "dryer $button_dryer_menu")
        }

        if(text_name_menu.text != ""){
            button_enable = true
        }
        else{
            button_enable = false
        }

        ButtonView(title = if(MENU_EDIT) "Save Edit Menu" else "Save Menu", modifier.constrainAs(buttonAddMenu) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, button_enable){
            Log.d("debug", "dryer $button_dryer_menu packet $button_packet_menu")
            if (MENU_EDIT){
//                Toast.makeText(context, "Edit Save Menu", Toast.LENGTH_SHORT).show()
                menuViewModel.updateMenu(
                    titleMenu = text_name_menu.text,
                    is_packet = button_packet_menu,
                    is_dryer = button_dryer_menu,
                    idMenu = MENU_ID,
                    navController = navController
                )
            }
            else{
//                Toast.makeText(context, "Save Menu", Toast.LENGTH_SHORT).show()
                menuViewModel.insertMenu(
                    name = text_name_menu.text,
                    is_packet = button_packet_menu,
                    is_dryer = button_dryer_menu,
                    navController = navController
                )
            }
        }
        if(menuViewModel.stateMenu == 4){
            Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
        }
    }
}