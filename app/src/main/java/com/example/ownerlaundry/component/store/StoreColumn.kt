package com.example.ownerlaundry.component.store

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ownerlaundry.api.store.StoreModel
import com.example.ownerlaundry.component.view.ViewStoreItem

@Composable
fun StoreColumn(
    storeModel: List<StoreModel>,
    navController: NavController,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
){
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(items = storeModel) { index, store ->
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                ViewStoreItem(
                    index = store.id!!.toString(),
                    nameStore = store.storeName.toString(),
                    cityStore = store.storeCity.toString(),
                    addressStore = store.storeAddress.toString(),
                    storeID = store.id.toString(),
                    passwordStore = store.passwordOwner.toString(),
                    navController = navController
                ){}
            }
        }
    }
}

