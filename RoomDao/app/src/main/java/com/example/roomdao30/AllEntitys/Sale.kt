package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.roomdao30.AllContracts.SaleContract
import java.math.BigDecimal
import java.security.Timestamp
import java.time.Instant
import java.util.*

//@Entity(tableName = "sales",
//    foreignKeys = [
//        ForeignKey(
//            entity=Employee::class,
//            parentColumns = ["id"],
//            childColumns = ["id_employee"]
//        ),
//    ForeignKey(
//        entity = Product::class,
//        parentColumns =["id"],
//        childColumns = ["id_products"]
//        )
//    ]
//)
@Entity(tableName = "sales")
data class Sale(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SaleContract.Columns.ID)
    val id:Int,
    @ColumnInfo(name = SaleContract.Columns.ID_PRODUCT,index=true)
    val id_products:Int,
    @ColumnInfo(name = SaleContract.Columns.PAY_DATE)
    val pay_date: Instant,
    @ColumnInfo(name = SaleContract.Columns.QUANTITY)
    val quantity:Int,
    @ColumnInfo(name = SaleContract.Columns.TOTAL_PRICE)
    val total_price:BigDecimal,
    @ColumnInfo(name = SaleContract.Columns.ID_EMPLOYEE, index = true)
    val id_employee:Int
    )
