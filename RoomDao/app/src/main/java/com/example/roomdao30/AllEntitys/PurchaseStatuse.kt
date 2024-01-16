package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.PurchasesStatuseContract

@Entity(tableName =PurchasesStatuseContract.TABLE_NAME)
data class PurchaseStatuse(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PurchasesStatuseContract.Columns.ID)
    val id: Int,
    @ColumnInfo(name = PurchasesStatuseContract.Columns.TITLE)
    val title: String
)
