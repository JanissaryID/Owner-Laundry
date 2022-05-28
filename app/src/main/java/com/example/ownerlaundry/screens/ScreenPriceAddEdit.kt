package com.example.ownerlaundry.screens

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.api.menu.MenuModel
import com.example.ownerlaundry.api.menu.MenuViewModel
import com.example.ownerlaundry.api.price.PriceViewModel
import com.example.ownerlaundry.component.ButtonView
import com.example.ownerlaundry.component.ViewButtonMenu
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.component.view.ViewTopBarEdit
import com.example.ownerlaundry.navigation.Screens


@Composable
fun ScreenPriceAddEdit(
    menuViewModel: MenuViewModel,
    navController: NavController,
    priceViewModel: PriceViewModel,
//    settingViewModel: SettingViewModel,
//    machineViewModel: MachineViewModel,
//    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBarEdit(
            navController = navController,
            title = if(PRICE_EDIT) "Edit Price" else TITLE_SCREEN[6],
            screenBack = Screens.Price.route,
        ) },
        backgroundColor = MaterialTheme.colors.background
    ){
        WallPriceAddEdit(
            menuViewModel = menuViewModel,
            navController = navController,
            priceViewModel = priceViewModel,
//            machineViewModel = machineViewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallPriceAddEdit(
    menuViewModel: MenuViewModel,
    navController: NavController,
    priceViewModel: PriceViewModel,
//    machineViewModel: MachineViewModel
) {
    val dataName = listOf(
        "Name Price",
        "Price",
        "Time Price",
    )

    val selectionMenu = listOf("Giant", "Titan")

    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var expanded by remember { mutableStateOf(false) }

    var button_enable by remember { mutableStateOf(false) }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val rotationState by animateFloatAsState(
        targetValue = (
            if (expanded){
                180f
            } else{
                0f
            }
        )
    )

    var menuPrice: List<MenuModel> by remember {
        mutableStateOf(listOf())
    }

    menuPrice = menuViewModel.menuListResponse

    var selectedText by remember { mutableStateOf(PRICE_MENU_TITLE) }
    var selectedPacket by remember { mutableStateOf(PRICE_PACKET) }
    var selectedDryer by remember { mutableStateOf(PRICE_DRYER) }
    var selectedIdMenu by remember { mutableStateOf(PRICE_MENU_ID) }

    var selected_index by remember {mutableStateOf(PRICE_CLASS)}

    val on_click_index = { index: Int -> selected_index = index}

    var text_name_price by remember { mutableStateOf(TextFieldValue(PRICE_TITLE)) }
    var text_price by remember { mutableStateOf(TextFieldValue(PRICE)) }
    var text_time_price by remember { mutableStateOf(TextFieldValue(PRICE_TIME)) }

//    Toast.makeText(context, "Menu Edit $MENU_EDIT $MENU_ID", Toast.LENGTH_SHORT).show()

    ConstraintLayout(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        .fillMaxSize()
    ) {

//        val content = createRef()
        val (TextName, TextPrice, TextTime, ClassMachine, MenuPick, ButtonAddPrice) = createRefs()
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
            value = text_name_price,
            label = { Text(text = dataName[0]) },
            onValueChange = {
                text_name_price = it
            }
        )

        OutlinedTextField(
            keyboardOptions  = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .constrainAs(TextPrice) {
                    top.linkTo(TextName.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.onSurface,
                focusedLabelColor = MaterialTheme.colors.onSurface,
                textColor = MaterialTheme.colors.onSurface,
                cursorColor = MaterialTheme.colors.onSurface
            ),
            value = text_price,
            label = { Text(text = dataName[1]) },
            onValueChange = {
                text_price = it
            }
        )

        OutlinedTextField(
            keyboardOptions  = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .constrainAs(TextTime) {
                    top.linkTo(TextPrice.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.onSurface,
                focusedLabelColor = MaterialTheme.colors.onSurface,
                textColor = MaterialTheme.colors.onSurface,
                cursorColor = MaterialTheme.colors.onSurface
            ),
            value = text_time_price,
            label = { Text(text = dataName[2]) },
            onValueChange = {
                text_time_price = it
            }
        )

        Box(modifier = modifier
            .constrainAs(ClassMachine) {
                top.linkTo(TextTime.bottom, 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            contentAlignment = Alignment.Center) {
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                modifier = modifier.wrapContentWidth()
            ){
                itemsIndexed(items = selectionMenu){index, menu ->
                    ViewButtonMenu(
                        title = menu,
                        index = if(selected_index != index){
                            index
                        }  else -1,
                        selected = if(selected_index == index) false else true,
                        onClick = on_click_index,
//                        priceViewModel = priceViewModel,
                        color = MaterialTheme.colors.surface
                    )
                }
            }
        }

        Column(modifier = modifier.constrainAs(MenuPick) {
            top.linkTo(ClassMachine.bottom, 8.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {
                    selectedText = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textfieldSize = coordinates.size.toSize()
                    },
                trailingIcon = {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = "contentDescription",
                        Modifier
                            .clickable { expanded = !expanded }
                            .rotate(rotationState))
                },
                readOnly = true,
                enabled = true
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = modifier.width(with(LocalDensity.current){textfieldSize.width.toDp()})
            ) {
            menuPrice.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label.priceMenu.toString()
                    selectedPacket = label.isPacket!!
                    selectedDryer = label.isDryer!!
                    selectedIdMenu = label.id.toString()
                    expanded = false
                }) {
                    Text(text = label.priceMenu.toString())
                }
            }
            }
        }

        if(text_name_price.text != "" && text_price.text != "" && text_time_price.text != "" && selected_index != -1 && selectedText != ""){
            button_enable = true
        }
        else{
            button_enable = false
        }

        ButtonView(title = if(PRICE_EDIT) "Save Edit Price" else "Save Price", modifier.constrainAs(ButtonAddPrice) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, button_enable){
//            Log.d("debug", "dryer $button_dryer_menu packet $button_packet_menu")
            if (PRICE_EDIT){
//                Toast.makeText(context, "Edit Save Price", Toast.LENGTH_SHORT).show()
                priceViewModel.updatePrice(
                    priceTitle = text_name_price.text,
                    price = text_price.text,
                    priceTime = text_time_price.text.toInt(),
                    isPacket = selectedPacket,
                    dryerNormal = selectedDryer,
                    classMachine = if(selected_index == 0) false else true,
                    menuID = selectedIdMenu,
                    navController = navController,
                    idPrice = PRICE_ID
                )
            }
            else{
//                Toast.makeText(context, "Save Price", Toast.LENGTH_SHORT).show()
                priceViewModel.insertPrice(
                    priceTitle = text_name_price.text,
                    price = text_price.text,
                    priceTime = text_time_price.text.toInt(),
                    isPacket = selectedPacket,
                    dryerNormal = selectedDryer,
                    classMachine = if(selected_index == 0) false else true,
                    menuID = selectedIdMenu,
                    navController = navController
                )
            }
        }
        if(menuViewModel.stateMenu == 4){
            Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
        }
    }
}