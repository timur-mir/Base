package com.example.roomdao30.AllEntitys

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.OfficialCarContract

@Entity(tableName =OfficialCarContract.TABLE_NAME)
data class OfficialCar(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val car_brand:String
)

