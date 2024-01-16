package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.OfficialCarContract
import com.example.roomdao30.AllContracts.PhoneNumberContract
import com.example.roomdao30.AllContracts.PricesContract

@Entity(tableName = PhoneNumberContract.TABLE_NAME)
data class PhoneNumber(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = PhoneNumberContract.Columns.PhoneNumber)
    val phonenumb:String,
    @ColumnInfo(name = PhoneNumberContract.Columns.EMAIL)
    val email:String,
    @ColumnInfo(name = PhoneNumberContract.Columns.LOGIN)
    val logincl:String
)