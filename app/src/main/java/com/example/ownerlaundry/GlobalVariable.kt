package com.example.ownerlaundry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.example.ownerlaundry.api.qris.QrisModel
import com.example.ownerlaundry.api.transaction.TransactionModel

val TITLE_SCREEN = listOf("Store", "Add Store", "Qris", "Menu", "Add Menu Price", "Price", "Add Price", "Transaction")

//Network Variable
var KEY_URL: String by mutableStateOf("")

//Store Variable
var STORE_ID: String by mutableStateOf("")
var STORE_NAME: String by mutableStateOf("")
var STORE_ADDRESS: String by mutableStateOf("")
var STORE_CITY: String by mutableStateOf("")
var STORE_PASSWORD: String by mutableStateOf("")
var STORE_EDIT: Boolean by mutableStateOf(false)

//Qris Variable
var QRIS_EDIT: Boolean by mutableStateOf(false)
var QRIS_ID: String by mutableStateOf("")
var QRIS_DATA: ArrayList<QrisModel> by mutableStateOf(arrayListOf())

//Menu Price Variable
var MENU_ID: String by mutableStateOf("")
var MENU_TITLE: String by mutableStateOf("")
var MENU_PACKET: Boolean by mutableStateOf(false)
var MENU_DRYER: Boolean by mutableStateOf(false)
var MENU_SERVICE: Boolean by mutableStateOf(false)
var MENU_EDIT: Boolean by mutableStateOf(false)

//Price Variable
var PRICE_EDIT: Boolean by mutableStateOf(false)
var PRICE_ID: String by mutableStateOf("")
var PRICE_TITLE: String by mutableStateOf("")
var PRICE: String by mutableStateOf("")
var PRICE_TIME: String by mutableStateOf("")
var PRICE_CLASS: Int by mutableStateOf(-1)
var PRICE_PACKET: Boolean by mutableStateOf(false)
var PRICE_DRYER: Boolean by mutableStateOf(false)
var PRICE_MENU_ID: String by mutableStateOf("")
var PRICE_MENU_TITLE: String by mutableStateOf("")

//Transaction Variable
var IS_DIALOG_OPEN =  mutableStateOf(false)
var DATE_PICK: String by mutableStateOf("")

//Excel Variable
var EXCEL_VALUE:List<TransactionModel> by mutableStateOf(arrayListOf())

//Machine Variable
var MACHINE_EDIT: Boolean by mutableStateOf(false)
var MACHINE_ID: String by mutableStateOf("")
var MACHINE_CLASS: Int by mutableStateOf(-1)
var MACHINE_TYPE: Int by mutableStateOf(-1)
var MACHINE_NUMBER: Int by mutableStateOf(0)

//Page Screen Variable
var PAGE_SCREEN: String by mutableStateOf("")

//var PROGRESS_DELETE: Boolean by mutableStateOf(false)

//Setting Variable
//var STORE_NAME_ADD by mutableStateOf(TextFieldValue(""))
//var STORE_ADDRESS_ADD by mutableStateOf(TextFieldValue(""))
//var STORE_CITY_ADD by mutableStateOf(TextFieldValue(""))
//var STORE_PASSWORD_ADD by mutableStateOf(TextFieldValue(""))