package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.ClientsContract

//@Entity(tableName ="products",indices = [
//    Index("id_pr")
//])
@Entity(tableName ="products")
data class Product(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_pr", index = true)
    val id:Int,
    @ColumnInfo(name = "title_pr")
    val title:String,
    @ColumnInfo(name = "avatar_pr")
    val avatar: String,
    @ColumnInfo(name = "description_pr")
    val description:String
)
