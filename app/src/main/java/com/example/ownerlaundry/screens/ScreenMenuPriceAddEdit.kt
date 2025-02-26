package com.example.ownerlaundry.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.R
import com.example.ownerlaundry.api.menu.MenuViewModel
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.component.ButtonRadio
import com.example.ownerlaundry.component.ButtonView
import com.example.ownerlaundry.component.view.ViewDialogLoading
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.component.view.ViewTopBarEdit
import com.example.ownerlaundry.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMenuPriceAddEdit(
    menuViewModel: MenuViewModel,
    navController: NavController,
) {
    val context = LocalContext.current

    PAGE_SCREEN = "add_menu_price_screen"

    Scaffold(
        topBar = { ViewTopBarEdit(
            navController = navController,
            title = if (MENU_EDIT) stringResource(R.string.Edit_menu_Price) else stringResource(R.string.Add_menu_price),
            screenBack = Screens.MenuPrice.route,
        ) },
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
        stringResource(R.string.Name_menu),
        stringResource(R.string.Paket),
        stringResource(R.string.Pengering),
        stringResource(R.string.Service),
        stringResource(R.string.Not_Packet),
        stringResource(R.string.Not_Dryer),
        stringResource(R.string.Not_Service)
    )
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var button_enable by remember { mutableStateOf(false) }
    var button_clicked by remember { mutableStateOf(false) }

    var text_name_menu by remember { mutableStateOf(TextFieldValue(MENU_TITLE)) }
    var button_packet_menu by remember { mutableStateOf(MENU_PACKET) }
    var button_dryer_menu by remember { mutableStateOf(MENU_DRYER) }
    var button_service_menu by remember { mutableStateOf(MENU_SERVICE) }

//    Toast.makeText(context, "Menu Edit $MENU_EDIT $MENU_ID", Toast.LENGTH_SHORT).show()

    ConstraintLayout(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        .fillMaxSize()
    ) {

//        val content = createRef()
        val (TextName, buttonPacket, buttonDryer, buttonAddMenu, buttonService) = createRefs()
        val modifier = Modifier

        OutlinedTextField(
            enabled = !button_clicked,
            modifier = modifier
                .fillMaxWidth()
                .constrainAs(TextName) {
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
            value = text_name_menu,
            label = { Text(text = dataName[0]) },
            onValueChange = {
                text_name_menu = it
            }
        )
        
        ButtonRadio(
            stateButton = button_packet_menu,
            title = if(!button_packet_menu) dataName[1] else dataName[4],
            color = MaterialTheme.colorScheme.surface,
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
            title = if(!button_dryer_menu) dataName[2] else dataName[5],
            color = MaterialTheme.colorScheme.surface,
            modifier = modifier.constrainAs(buttonDryer) {
                top.linkTo(buttonPacket.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ){
            button_dryer_menu = it
//            Log.d("debug", "dryer $button_dryer_menu")
        }

        ButtonRadio(
            stateButton = button_service_menu,
            title = if(!button_service_menu) dataName[3] else dataName[6],
            color = MaterialTheme.colorScheme.surface,
            modifier = modifier.constrainAs(buttonService) {
                top.linkTo(buttonDryer.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ){
            button_service_menu = it
//            Log.d("debug", "dryer $button_dryer_menu")
        }

        if(text_name_menu.text != "" && !button_clicked){
            button_enable = true
        }
        else{
            button_enable = false
        }

        ButtonView(title = if(MENU_EDIT) stringResource(R.string.Save_Edit_Menu) else stringResource(
                    R.string.Save_menu), modifier.constrainAs(buttonAddMenu) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, button_enable){
            IS_DIALOG_OPEN.value = true
            Log.d("debug", "dryer $button_dryer_menu packet $button_packet_menu")
            if (MENU_EDIT){
//                Toast.makeText(context, "Edit Save Menu", Toast.LENGTH_SHORT).show()
                button_clicked = true
                button_enable = false
                menuViewModel.updateMenu(
                    titleMenu = text_name_menu.text,
                    is_packet = button_packet_menu,
                    is_dryer = button_dryer_menu,
                    idMenu = MENU_ID,
                    is_service = button_service_menu,
                    navController = navController
                )
            }
            else{
//                Toast.makeText(context, "Save Menu", Toast.LENGTH_SHORT).show()
                button_clicked = true
                button_enable = false
                menuViewModel.insertMenu(
                    name = text_name_menu.text,
                    is_packet = button_packet_menu,
                    is_dryer = button_dryer_menu,
                    is_service = button_service_menu,
                    navController = navController
                )
            }
        }

        if(menuViewModel.stateMenu == 4){
            Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
        }

        if (IS_DIALOG_OPEN.value){
            ViewDialogLoading()
        }
    }
}