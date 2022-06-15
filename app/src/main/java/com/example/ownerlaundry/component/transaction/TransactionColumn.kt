package com.example.ownerlaundry.component.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ownerlaundry.api.transaction.TransactionModel
import com.example.ownerlaundry.component.view.ViewTransactionItem

@Composable
fun TransactionColumn(
    transcationModel: List<TransactionModel>,
    navController: NavController,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
){
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(items = transcationModel) { index, transaction ->
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                ViewTransactionItem(
                    title_machine = transaction.transactionMenuMachine.toString(),
                    type_machine = transaction.transactionTypeMenu.toString(),
                    price = transaction.transactionPrice.toString(),
                    type_payment = transaction.transactionTypePayment.toString()
                )
            }
        }
    }
}