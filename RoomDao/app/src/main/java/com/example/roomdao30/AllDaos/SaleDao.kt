package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.SaleContract
import com.example.roomdao30.AllEntitys.Sale
import com.example.roomdao30.TypeConvertersForBase

@Dao
@TypeConverters(TypeConvertersForBase::class)
interface SaleDao {


    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertSale(sale: Sale)

    @Query("SELECT*FROM ${SaleContract.TABLE_NAME}")
    suspend fun getAllSales():List<Sale>

    @Query("SELECT*FROM ${SaleContract.TABLE_NAME} WHERE ${SaleContract.Columns.ID}=:saleId")
    suspend fun getSaleById(saleId:Int): Sale?

    @Update
    suspend fun updateSale(sale: Sale)

    @Delete
    suspend fun removeSale(sale: Sale)

    @Query("DELETE FROM ${SaleContract.TABLE_NAME} WHERE ${SaleContract.Columns.ID}=:saleId")
    suspend fun removeSaleById(saleId: Int)
}