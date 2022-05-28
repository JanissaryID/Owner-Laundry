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
fun ViewMachineItem(
    index: String,
    id_machine: String,
    number_machine: Int,
    type_machine: Boolean,
    class_machine: Boolean,
    navController: NavController,
    onClick: (String) -> Unit
) {
    val context = LocalContext.current

    var expandedState by remember { mutableStateOf(false) }

    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier.clickable {
            expandedState = !expandedState
            onClick.invoke(index)
            MACHINE_ID = id_machine
            MACHINE_CLASS = if(class_machine == true) 1 else 0
            MACHINE_TYPE = if(type_machine == true) 1 else 0
            MACHINE_NUMBER = number_machine
            MACHINE_EDIT = true
            PAGE_SCREEN = Screens.AddEditMachine.route
            navController.navigate(route = Screens.AddEditMachine.route)
//            Toast.makeText(context, "Machine Number $number_machine", Toast.LENGTH_SHORT).show()
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

                val (PriceTitle, PriceType, Price) = createRefs()

                Text(
                    color = MaterialTheme.colors.primary,
                    text = number_machine.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
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
                    text = if(type_machine == true) "Dryer" else "Washer",
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
                    color = MaterialTheme.colors.primary,
                    text = if (class_machine == true) "Titan" else "Giant",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(Price)
                        {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                )
            }
        }
    }
}