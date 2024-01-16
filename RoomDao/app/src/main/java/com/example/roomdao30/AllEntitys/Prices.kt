package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.AuthorsContract
import com.example.roomdao30.AllContracts.PricesContract
import java.math.BigDecimal
import java.time.Instant
@Entity(tableName = PricesContract.TABLE_NAME)
data class Prices(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PricesContract.Columns.ID)
    val id:Int,
    @ColumnInfo(name = PricesContract.Columns.PRODUCT_ID)
    val product_id:Int,
    @ColumnInfo(name = PricesContract.Columns.PRICE)
    val price:BigDecimal,
    @ColumnInfo(name = PricesContract.Columns.CREATE_AT_PRICE)
    val create_at:Instant
)
