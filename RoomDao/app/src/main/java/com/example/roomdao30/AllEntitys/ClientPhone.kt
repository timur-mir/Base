package com.example.roomdao30.AllEntitys

import androidx.room.Entity

@Entity(tableName ="clientphones")
data class ClientPhone(
    val phone_number:String,
    val login:String
)
