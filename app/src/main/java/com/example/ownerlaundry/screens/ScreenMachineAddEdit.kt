package com.example.ownerlaundry.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.api.machine.MachineViewModel
import com.example.ownerlaundry.component.ButtonView
import com.example.ownerlaundry.component.ViewButtonMenu
import com.example.ownerlaundry.component.view.ViewDialogLoading
import com.example.ownerlaundry.component.view.ViewTopBarEdit
import com.example.ownerlaundry.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMachineAddEdit(
    machineViewModel: MachineViewModel,
    navController: NavController,
) {
    val context = LocalContext.current

    PAGE_SCREEN = "add_machine_screen"

    Scaffold(
        topBar = { ViewTopBarEdit(
            navController = navController,
            title = if (MENU_EDIT) "Edit Machine" else TITLE_SCREEN[9],
            screenBack = Screens.Machine.route,
        ) },
    ){
        WallMenuPriceAddEdit(
            machineViewModel = machineViewModel,
            navController = navController,
//            priceViewModel = priceViewModel,
//            machineViewModel = machineViewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallMenuPriceAddEdit(
    machineViewModel: MachineViewModel,
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    machineViewModel: MachineViewModel
) {
    val dataName = listOf(
        "Number Machine",
        "Packet",
        "Dryer",
    )

    val selectionMenuClass = listOf("Giant", "Titan")
    val selectionMenuType = listOf("Washer", "Dryer")

    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var button_enable by remember { mutableStateOf(false) }

    var selected_index_class by remember {mutableStateOf(MACHINE_CLASS)}
    var selected_index_type by remember {mutableStateOf(MACHINE_TYPE)}

    val on_click_index_class = { index: Int -> selected_index_class = index}
    val on_click_index_type = { index: Int -> selected_index_type = index}

    var text_number by remember { mutableStateOf(TextFieldValue(MACHINE_NUMBER.toString())) }
//    var button_packet_menu by remember { mutableStateOf(MENU_PACKET) }
//    var button_dryer_menu by remember { mutableStateOf(MENU_DRYER) }

//    Toast.makeText(context, "Menu Edit $MENU_EDIT $MENU_ID", Toast.LENGTH_SHORT).show()

    ConstraintLayout(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        .fillMaxSize()
    ) {

//        val content = createRef()
        val (TextName, ClassMachine, TypeMachine, buttonAddMenu) = createRefs()
        val modifier = Modifier

        OutlinedTextField(
            keyboardOptions  = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
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
            value = text_number,
            label = { Text(text = dataName[0]) },
            onValueChange = {
                text_number = it
            }
        )

        Box(modifier = modifier
            .constrainAs(ClassMachine) {
                top.linkTo(TextName.bottom, 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            contentAlignment = Alignment.Center) {
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                modifier = modifier.wrapContentWidth()
            ){
                itemsIndexed(items = selectionMenuClass){index, menu ->
                    ViewButtonMenu(
                        title = menu,
                        index = if(selected_index_class != index){
                            index
                        }  else -1,
                        selected = if(selected_index_class == index) false else true,
                        onClick = on_click_index_class,
//                        priceViewModel = priceViewModel,
                        color = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }

        Box(modifier = modifier
            .constrainAs(TypeMachine) {
                top.linkTo(ClassMachine.bottom, 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            contentAlignment = Alignment.Center) {
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                modifier = modifier.wrapContentWidth()
            ){
                itemsIndexed(items = selectionMenuType){index, menu ->
                    ViewButtonMenu(
                        title = menu,
                        index = if(selected_index_type != index){
                            index
                        }  else -1,
                        selected = if(selected_index_type == index) false else true,
                        onClick = on_click_index_type,
//                        priceViewModel = priceViewModel,
                        color = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }

        if(text_number.text != "" && selected_index_class != -1 && selected_index_type != -1){
            button_enable = true
        }
        else{
            button_enable = false
        }

        ButtonView(title = if(MACHINE_EDIT) "Save Edit Machine" else "Save Machine", modifier.constrainAs(buttonAddMenu) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, button_enable){
            IS_DIALOG_OPEN.value = true
//            Log.d("debug", "dryer $button_dryer_menu packet $button_packet_menu")
            if (MACHINE_EDIT){
                IS_DIALOG_OPEN.value = true
//                Toast.makeText(context, "Edit Save Machine", Toast.LENGTH_SHORT).show()
                machineViewModel.updateMachine(
                    navController = navController,
                    number = text_number.text.toInt(),
                    type_machine = if(selected_index_type == 0) false else true,
                    class_machine = if(selected_index_class == 0) false else true,
                    idMachine = MACHINE_ID
                )

            }
            else{
                IS_DIALOG_OPEN.value = true
//                Toast.makeText(context, "Save Machine", Toast.LENGTH_SHORT).show()
                machineViewModel.insertMachine(
                    number = text_number.text.toInt(),
                    class_machine = if(selected_index_class == 0) false else true,
                    type_machine = if(selected_index_type == 0) false else true,
                    navController = navController
                )
            }
        }
        if(machineViewModel.stateMachine == 4){
            Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
        }
        if (IS_DIALOG_OPEN.value){
            ViewDialogLoading()
        }
    }
}