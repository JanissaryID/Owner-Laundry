package com.example.ownerlaundry.component.menu

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
import com.example.ownerlaundry.api.menu.MenuModel
import com.example.ownerlaundry.component.view.ViewMenuItem

@Composable
fun MenuColumn(
    menuModel: List<MenuModel>,
    navController: NavController,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
){
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(items = menuModel) { index, menu ->
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                ViewMenuItem(
                    index = menu.id!!.toString(),
                    title_Menu = menu.priceMenu.toString(),
                    is_packet = menu.isPacket!!,
                    is_dryer = menu.isDryer!!,
                    id_menu = menu.id.toString(),
                    navController = navController
                ){}
            }
        }
    }
}