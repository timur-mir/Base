package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.AuthorsContract
import com.example.roomdao30.AllContracts.PricesContract
import com.example.roomdao30.AllContracts.PurchasesContract
import java.time.Instant
@Entity(tableName = PurchasesContract.TABLE_NAME)
data class Purchases(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PurchasesContract.Columns.ID)
    val id:Int,
    @ColumnInfo(name = PurchasesContract.Columns.CLIENT_ID)
    val client_id:Int,
    @ColumnInfo(name = PurchasesContract.Columns.STATUS_ID)
    val statuse_id:Int,
    @ColumnInfo(name = PurchasesContract.Columns.CREATE_AT)
    val created_at_purch:Instant,
    @ColumnInfo(name = PurchasesContract.Columns.PAYED_AT)
    val payed_at: Instant?
)