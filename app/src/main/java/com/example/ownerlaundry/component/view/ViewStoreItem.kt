package com.example.ownerlaundry.component.view

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.example.ownerlaundry.ui.theme.OwnerLaundryTheme
import com.example.ownerlaundry.navigation.Screens

@Composable
fun ViewStoreItem(
    index: String,
    nameStore: String,
    addressStore: String,
    cityStore: String,
    storeID:String,
    passwordStore: String,
    navController: NavController,
    onClick: (String) -> Unit
) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.clickable {
            onClick.invoke(index)
            STORE_NAME = nameStore
            STORE_ID = storeID
            STORE_ADDRESS = addressStore
            STORE_CITY = cityStore
            STORE_PASSWORD = passwordStore
            STORE_EDIT = true
            QRIS_DATA.clear()
            navController.navigate(route = Screens.Menu.route)
            Toast.makeText(context, "Clicked $STORE_NAME", Toast.LENGTH_SHORT).show()
        }
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
                .fillMaxWidth()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {

                val (StoreName, StoreAddress, StoreCity, StoreImage, StoreLocation) = createRefs()

                Image(painter = painterResource(
                    id = R.drawable.ic_storeicon),
                    contentDescription = "Store Image",
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(StoreImage)
                        {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                        }
                )

                Image(painter = painterResource(
                    id = R.drawable.ic_locationicon),
                    contentDescription = "Store Image",
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(StoreLocation)
                        {
                            top.linkTo(StoreName.bottom)
                            start.linkTo(StoreImage.end, 4.dp)
                            bottom.linkTo(StoreAddress.top)
                        }
                )

                Text(
                    text = nameStore.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(StoreName)
                        {
                            top.linkTo(StoreImage.top, 14.dp)
                            start.linkTo(StoreImage.end, 4.dp)
                        }
                )

                Text(
                    text = cityStore,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(StoreCity)
                        {
                            top.linkTo(StoreName.bottom)
                            start.linkTo(StoreLocation.end, 2.dp)
                            bottom.linkTo(StoreAddress.top)
                        }
                )
                Text(
                    text = addressStore,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.7f),
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(StoreAddress)
                        {
                            bottom.linkTo(StoreImage.bottom, 14.dp)
                            start.linkTo(StoreImage.end, 4.dp)
                        }
                )
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun SettingPreview() {
//    OwnerLaundryTheme {
//        ViewStoreItem(index = "1", nameStore = "Alfa Omega Patimura", cityStore = "Salatiga", addressStore = "Jl. Patimura", storeID = "1", navController = rememberNavController()){}
//    }
//}