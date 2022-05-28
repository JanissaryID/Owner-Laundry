package com.example.ownerlaundry.component.view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.navigation.Screens

@Composable
fun ViewPriceItem(
    id_price: String,
    index: String,
    title_price: String,
    type_price: String,
    time_price: Int,
    price: String,
    navController: NavController,
    price_class: Int,
    price_menu_id: String,
    price_packet: Boolean,
    price_dryer: Boolean,
    onClick: (String) -> Unit
) {
    val context = LocalContext.current

    var expandedState by remember { mutableStateOf(false) }

    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier.clickable {
            expandedState = !expandedState
            onClick.invoke(index)
            PRICE_TITLE = title_price
            PRICE = price
            PRICE_TIME = time_price.toString()
            PRICE_CLASS = price_class
            PRICE_ID = id_price
            PRICE_MENU_ID = price_menu_id
            PRICE_MENU_TITLE = type_price
            PRICE_EDIT = true
            PRICE_PACKET = price_packet
            PRICE_DRYER = price_dryer
            PAGE_SCREEN = Screens.AddEditPrice.route
            navController.navigate(route = Screens.AddEditPrice.route)
//            Toast.makeText(context, "Menu Price $title_price", Toast.LENGTH_SHORT).show()
        }
    ) {
        Column(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
            .fillMaxWidth()) {

            ConstraintLayout(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {

                val (PriceTitle, PriceType, Price, PriceTime) = createRefs()

                Text(
                    color = MaterialTheme.colors.primary,
                    text = title_price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(PriceTitle)
                        {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                )

                Text(
                    color = MaterialTheme.colors.primary,
                    text = type_price,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(PriceType)
                        {
                            top.linkTo(PriceTitle.bottom, 8.dp)
                            start.linkTo(parent.start)
                        }
                )

                Text(
                    color = Color.Green,
                    text = price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(Price)
                        {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                )

                Text(
                    text = time_price.toString(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(start = 8.dp)
                        .constrainAs(PriceTime)
                        {
                            top.linkTo(Price.bottom, 8.dp)
                            end.linkTo(parent.end)
                        }
                )
            }
        }
    }
}