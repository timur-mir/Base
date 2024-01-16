package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.AuthorsContract
import com.example.roomdao30.AllContracts.ClientsContract
import com.example.roomdao30.AllContracts.ProvidersContract

@Entity(tableName =ProvidersContract.TABLE_NAME)
data class Provider(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ProvidersContract.Columns.ID_PROV)
    val id_provider:Int,
    @ColumnInfo(name = ProvidersContract.Columns.ORGANIZATION)
    val organization:String,
    @ColumnInfo(name = ProvidersContract.Columns.ADDRESS)
    val address:String,
    @ColumnInfo(name = ProvidersContract.Columns.PHONE_NUMBER)
    val phone_number:String

)
