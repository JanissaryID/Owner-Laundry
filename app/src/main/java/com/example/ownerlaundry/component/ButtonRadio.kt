package com.example.ownerlaundry.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonRadio(
    stateButton: Boolean,
    title: String,
    color: Color,
    modifier: Modifier,
    onClick: (Boolean) -> Unit
) {
    var buttonState by remember { mutableStateOf(stateButton) }

    Card(
        modifier = modifier.padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp),
        shape = RoundedCornerShape(40),
    ) {
        Surface(
            border = BorderStroke(5.dp, MaterialTheme.colorScheme.primary),
            modifier = modifier
                .height(56.dp)
                .fillMaxWidth()
                .clickable {
                    buttonState = !buttonState
                    onClick.invoke(buttonState)
                },
            color = if (!buttonState) color else MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(40),
            contentColor = if (!buttonState) color else MaterialTheme.colorScheme.primary,
        ){
            Text(
                color = if (!buttonState) MaterialTheme.colorScheme.primary else color,
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = modifier.wrapContentHeight().padding(
                    start = 24.dp,
                    end = 24.dp
                )
            )
        }
    }
}