package com.example.ownerlaundry.api.debug

import com.google.gson.annotations.SerializedName

data class DebugTransactionModel(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("time")
    val time: String? = null,

    @field:SerializedName("number_machine")
    val NumberMachine: Int? = null,

    @field:SerializedName("type_menu")
    val TypeMenu: Boolean? = null,

    @field:SerializedName("is_packet")
    val isPacket: Boolean? = null,
)