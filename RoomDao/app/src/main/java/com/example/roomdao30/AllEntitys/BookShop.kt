package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.ClientsContract

//@Entity(tableName ="bookshop",indices = [
//        androidx.room.Index("id", unique = true)
//])
@Entity(tableName ="bookshop")
data class BookShop(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_shop", index = true)
    val id:Int,
    val title:String,
    val address: String,
    val phone_number:String
)
