package com.example.roomdao30.AllRelation


import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.roomdao30.AllContracts.ClientsContract
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Rating

data class ClientWithRating(
    @ColumnInfo(name = ClientsContract.Columns.LOGIN)
    val login:String,
    @ColumnInfo(name = ClientsContract.Columns.FIRST_NAME)
    val first_name:String,
    @ColumnInfo(name = ClientsContract.Columns.LAST_NAME)
    val last_name:String,
    @Relation(parentColumn = "login", entityColumn = "login")
    val ratingRequest: List<Rating>
)
