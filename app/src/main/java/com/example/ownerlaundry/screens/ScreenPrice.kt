package com.example.ownerlaundry.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.R
import com.example.ownerlaundry.api.price.PriceViewModel
import com.example.ownerlaundry.component.price.PriceLoadData
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenPrice(
    priceViewModel: PriceViewModel,
    navController: NavController,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBar(
            navController = navController,
            title = stringResource(R.string.Price),
            screenBack = Screens.Menu.route
        ) },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(24.dp),
                onClick = {
                    PRICE_TITLE = ""
                    PRICE = ""
                    PRICE_TIME = ""
                    PRICE_CLASS = -1
                    PRICE_ID = ""
                    PRICE_MENU_ID = ""
                    PRICE_MENU_TITLE = ""
                    PRICE_EDIT = false
                    PRICE_PACKET = false
                    PRICE_DRYER = false
                    navController.navigate(route = Screens.AddEditPrice.route)
//                    Toast.makeText(context, "Add Price Clicked", Toast.LENGTH_SHORT).show()
                }) {
                Icon(imageVector = Icons.Default.Add, "Add Price")
            }
        }
    ){
        WallPrice(
            priceViewModel = priceViewModel,
            navController = navController,
        )
    }
}

@Composable
fun WallPrice(
    priceViewModel: PriceViewModel,
    navController: NavController,
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val stateMenu = priceViewModel.statePrice
    val price = priceViewModel.priceListResponse

    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        PriceLoadData(
            selectedIndex = selectedIndex,
            onItemClick = onItemClick,
            price = price,
            priceState = stateMenu,
            navController = navController
        )
    }
}