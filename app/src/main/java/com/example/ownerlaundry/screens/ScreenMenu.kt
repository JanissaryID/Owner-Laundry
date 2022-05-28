package com.example.ownerlaundry.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.R
import com.example.ownerlaundry.api.store.StoreViewModel
import com.example.ownerlaundry.component.store.StoreLoadData
import com.example.ownerlaundry.component.view.ViewStoreItem
import com.example.ownerlaundry.component.view.ViewTopBar
import com.example.ownerlaundry.component.view.ViewTopBarHome
import com.example.ownerlaundry.component.view.ViewTopBarMenu
import com.example.ownerlaundry.navigation.Screens
import com.example.ownerlaundry.ui.theme.OwnerLaundryTheme

@Composable
fun ScreenMenu(
//    storeViewModel: StoreViewModel,
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    settingViewModel: SettingViewModel,
//    machineViewModel: MachineViewModel,
//    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { ViewTopBarMenu(
            navController = navController,
            title = STORE_NAME,
            screenBack = Screens.Home.route
        ) },
        backgroundColor = MaterialTheme.colors.background,
    ){
        WallMenu(
//            storeViewModel = storeViewModel
            navController = navController,
//            priceViewModel = priceViewModel,
//            machineViewModel = machineViewModel
        )
    }
}

@Composable
fun WallMenu(
//    storeViewModel: StoreViewModel
    navController: NavController,
//    priceViewModel: PriceViewModel,
//    machineViewModel: MachineViewModel
) {
    val context = LocalContext.current

    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Column() {
            Row() {
                Surface(
                    modifier = Modifier.height(160.dp).weight(1f),shape = RoundedCornerShape(20.dp), color = MaterialTheme.colors.primary
                ){
                    Card(
                        shape = RoundedCornerShape(17.dp),
                        modifier = Modifier.padding(5.dp).clickable {
//                            onClick.invoke(index)
//                            STORE_NAME = nameStore
                            navController.navigate(route = Screens.MenuPrice.route)
//                            Toast.makeText(context, "Clicked Menu", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        ConstraintLayout(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                        ) {
                            val (image, title) = createRefs()

                            Image(painter = painterResource(
                                id = R.drawable.ic_menu),
                                contentDescription = "Menu Image",
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .constrainAs(image)
                                    {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        bottom.linkTo(parent.bottom)
                                    }
                            )

                            Text(
                                text = "Menu",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colors.primary,
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .constrainAs(title)
                                    {
                                        top.linkTo(image.bottom)
                                        start.linkTo(image.start)
                                        end.linkTo(image.end)
                                        bottom.linkTo(parent.bottom, 24.dp)
                                    }
                            )
                        }
                    }
                }
                Spacer(Modifier.width(16.0.dp))
                Surface(
                    modifier = Modifier.height(160.dp).weight(1f),shape = RoundedCornerShape(20.dp), color = MaterialTheme.colors.primary
                ){
                    Card(
                        modifier = Modifier.padding(5.dp).clickable {
                            navController.navigate(route = Screens.Qris.route)
                            Toast.makeText(context, "Clicked Qris", Toast.LENGTH_SHORT).show()
                        },
                        shape = RoundedCornerShape(17.dp)
                    ) {
                        ConstraintLayout(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                        ) {
                            val (image, title) = createRefs()

                            Image(painter = painterResource(
                                id = R.drawable.ic_qris),
                                contentDescription = "Qris Image",
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .constrainAs(image)
                                    {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        bottom.linkTo(parent.bottom)
                                    }
                            )

                            Text(
                                text = "Qris",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colors.primary,
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .constrainAs(title)
                                    {
                                        top.linkTo(image.bottom)
                                        start.linkTo(image.start)
                                        end.linkTo(image.end)
                                        bottom.linkTo(parent.bottom, 24.dp)
                                    }
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(16.0.dp))
            Surface(
                modifier = Modifier.height(160.dp),shape = RoundedCornerShape(20.dp), color = MaterialTheme.colors.primary
            ){
                Card(
                    modifier = Modifier.padding(5.dp).clickable {
                        navController.navigate(route = Screens.Price.route)
//                        Toast.makeText(context, "Clicked Transaction", Toast.LENGTH_SHORT).show()
                    },
                    shape = RoundedCornerShape(17.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    ) {
                        val (image, title) = createRefs()

                        Image(painter = painterResource(
                            id = R.drawable.ic_dollar),
                            contentDescription = "Price Image",
                            modifier = Modifier
                                .wrapContentHeight()
                                .constrainAs(image)
                                {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
//                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                        )

                        Text(
                            text = "Price",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .wrapContentHeight()
                                .constrainAs(title)
                                {
                                    top.linkTo(parent.top)
                                    start.linkTo(image.end)
//                                    end.linkTo(par)
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.0.dp))
            Surface(
                modifier = Modifier.height(160.dp),shape = RoundedCornerShape(20.dp), color = MaterialTheme.colors.primary
            ){
                Card(
                    modifier = Modifier.padding(5.dp).clickable {
                        navController.navigate(route = Screens.ListTransactions.route)
//                        Toast.makeText(context, "Clicked Transaction", Toast.LENGTH_SHORT).show()
                    },
                    shape = RoundedCornerShape(17.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    ) {
                        val (image, title) = createRefs()

                        Image(painter = painterResource(
                            id = R.drawable.ic_bill),
                            contentDescription = "Transaction Image",
                            modifier = Modifier
                                .wrapContentHeight()
                                .constrainAs(image)
                                {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
//                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                        )

                        Text(
                            text = "Transaction",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .wrapContentHeight()
                                .constrainAs(title)
                                {
                                    top.linkTo(parent.top)
                                    start.linkTo(image.end)
//                                    end.linkTo(par)
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.0.dp))
            Surface(
                modifier = Modifier.height(160.dp),shape = RoundedCornerShape(20.dp), color = MaterialTheme.colors.primary
            ){
                Card(
                    modifier = Modifier.padding(5.dp).clickable {
                        navController.navigate(route = Screens.Machine.route)
//                        Toast.makeText(context, "Clicked Machine", Toast.LENGTH_SHORT).show()
                    },
                    shape = RoundedCornerShape(17.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    ) {
                        val (image, title) = createRefs()

                        Image(painter = painterResource(
                            id = R.drawable.ic_machine),
                            contentDescription = "Machine Image",
                            modifier = Modifier
                                .wrapContentHeight()
                                .constrainAs(image)
                                {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
//                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                        )

                        Text(
                            text = "Machine",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .wrapContentHeight()
                                .constrainAs(title)
                                {
                                    top.linkTo(parent.top)
                                    start.linkTo(image.end)
//                                    end.linkTo(par)
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SettingPreview() {
//    OwnerLaundryTheme {
//        WallMenu()
//    }
//}