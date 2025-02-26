package com.example.ownerlaundry.component.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ownerlaundry.DATE_PICK
import com.example.ownerlaundry.IS_DIALOG_OPEN
import com.example.ownerlaundry.R
import com.example.ownerlaundry.component.ButtonView
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewDialogCalendar() {

    val calendarState = rememberSelectableCalendarState()

    val context = LocalContext.current

    Dialog(onDismissRequest = { IS_DIALOG_OPEN.value = true }) {
        Card(modifier = Modifier
            .wrapContentHeight(),
            shape = RoundedCornerShape(10),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                SelectableCalendar(
                    calendarState = calendarState
                )
                var getDate = calendarState.selectionState.selection.joinToString { it.toString() }
                var picDate = getDate.split('-')
                if(picDate.size == 3){
                    DATE_PICK = "${picDate[2]}-${picDate[1]}-${picDate[0]}"
                }
                Spacer(Modifier.height(16.0.dp))
                ButtonView(title = stringResource(R.string.Pick_Date), enable = true
                ){
                    IS_DIALOG_OPEN.value = false
                    Toast.makeText(context, "$DATE_PICK", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}