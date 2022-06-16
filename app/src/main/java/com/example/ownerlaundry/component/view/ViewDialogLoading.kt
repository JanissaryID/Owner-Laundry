package com.example.ownerlaundry.component.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ownerlaundry.DATE_PICK
import com.example.ownerlaundry.IS_DIALOG_OPEN
import com.example.ownerlaundry.component.ButtonView
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewDialogLoading() {


    val context = LocalContext.current

    Dialog(onDismissRequest = { IS_DIALOG_OPEN.value = true }) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}