package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.ProvidersContract
import com.example.roomdao30.AllContracts.SupplyContract

@Entity(tableName = SupplyContract.TABLE_NAME)
data class Supply(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SupplyContract.Columns.ID_SUP)
    val id:Int,
    @ColumnInfo(name = SupplyContract.Columns.SUPPLY_SIZE)
    val supply_size:Int,
    @ColumnInfo(name = SupplyContract.Columns.ID_PROVIDER)
    val id_provider:Int,
    @ColumnInfo(name = SupplyContract.Columns.ID_PRODUCT)
    val id_product:Int
)
