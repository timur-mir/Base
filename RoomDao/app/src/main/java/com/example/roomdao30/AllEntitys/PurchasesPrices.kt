package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.PurchasesPricesContract


@Entity(tableName = PurchasesPricesContract.TABLE_NAME)
data class PurchasesPrices(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PurchasesPricesContract.Columns.ID)
    val id:Int,
    @ColumnInfo(name = PurchasesPricesContract.Columns.PRICE_ID)
    val price_id:Int,
    @ColumnInfo(name = PurchasesPricesContract.Columns.PURCHASE_ID)
    val purchase_id:Int,
    @ColumnInfo(name = PurchasesPricesContract.Columns.COUNT)
    val count:Int
)
