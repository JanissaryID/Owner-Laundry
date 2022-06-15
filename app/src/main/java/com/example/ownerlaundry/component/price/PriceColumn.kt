package com.example.ownerlaundry.component.price

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
import com.example.ownerlaundry.api.price.PriceModel
import com.example.ownerlaundry.component.view.ViewPriceItem

@Composable
fun PriceColumn(
    priceModel: List<PriceModel>,
    navController: NavController,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
){
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(items = priceModel) { index, price ->
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                ViewPriceItem(
                    id_price = price.id.toString(),
                    index = price.id.toString(),
                    title_price = price.priceTitle.toString(),
                    type_price = price.menu!![0]!!.priceMenu.toString(),
                    time_price = price.priceTime!!,
                    price = price.price.toString(),
                    price_class = if(price.priceClassMachine == false) 0 else 1,
                    price_menu_id = price.menu!![0]?.id.toString(),
                    price_packet = price.menu!![0]?.isPacket!!,
                    price_dryer = price.menu!![0]?.isDryer!!,
                    navController = navController
                ){}
            }
        }
    }
}