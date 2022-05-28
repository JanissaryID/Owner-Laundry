package com.example.ownerlaundry.component.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ViewTransactionItem(
    title_machine: String,
    type_machine: String,
    type_payment: String,
    price: String
) {
    val context = LocalContext.current

    Card(shape = RoundedCornerShape(20.dp),
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
                    text = title_machine,
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
                    text = type_machine,
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
                    text = type_payment,
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