package com.example.ownerlaundry.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.component.store.StoreLoadData
import com.example.ownerlaundry.component.view.ViewTopBarHome
import com.example.ownerlaundry.navigation.Screens
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenHome(
    storeViewModel: StoreViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    val current = LocalDateTime.now()

    val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val date = current.format(formatDay)
    DATE_PICK = date

    Scaffold(
        topBar = { ViewTopBarHome(
            navController = navController,
            title = TITLE_SCREEN[0]
        ) },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(24.dp),
                onClick = {
                    STORE_NAME = ""
                    STORE_ID = ""
                    STORE_ADDRESS = ""
                    STORE_CITY = ""
                    STORE_PASSWORD = ""
                    STORE_EDIT = false
                    navController.navigate(route = Screens.AddStore.route)
//                    Toast.makeText(context, "Add Store Clicked", Toast.LENGTH_SHORT).show()
                }) {
                Icon(imageVector = Icons.Default.Add, "Add Store")
            }
        }
    ){
        WallHome(
            storeViewModel = storeViewModel,
            navController = navController
        )
    }
}

@Composable
fun WallHome(
    storeViewModel: StoreViewModel,
    navController: NavController
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val stateStore = storeViewModel.stateStore
    val store = storeViewModel.storeListResponse

    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        StoreLoadData(
            selectedIndex = selectedIndex,
            onItemClick = onItemClick,
            store = store,
            storeState = stateStore,
            navController = navController
        )
    }
}