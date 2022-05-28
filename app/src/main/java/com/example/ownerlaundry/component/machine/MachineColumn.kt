package com.example.ownerlaundry.component.machine

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ownerlaundry.api.machine.MachineModel
import com.example.ownerlaundry.api.transaction.TransactionModel
import com.example.ownerlaundry.component.view.ViewMachineItem
import com.example.ownerlaundry.component.view.ViewTransactionItem

@Composable
fun MachineColumn(
    machineModel: List<MachineModel>,
    navController: NavController,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
){
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(items = machineModel) { index, machine ->
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                ViewMachineItem(
                    number_machine = machine.machineNumber!!,
                    type_machine = machine.machineType!!,
                    class_machine = machine.machineClass!!,
                    navController = navController,
                    index = machine.id.toString(),
                    id_machine = machine.id.toString()
                ){}
            }
        }
    }
}