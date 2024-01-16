package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.PurchasesPricesContract
import com.example.roomdao30.AllEntitys.PurchasesPrices
@Dao
interface PurchasesPricesDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertPurchPrice(purchPrice: PurchasesPrices)

    @Query("SELECT*FROM ${PurchasesPricesContract.TABLE_NAME}")
    suspend fun getAllPurchPricesBase():List<PurchasesPrices>

    @Query("SELECT*FROM ${PurchasesPricesContract.TABLE_NAME} WHERE ${PurchasesPricesContract.Columns.ID}=:purchPriceId")
    suspend fun getPurchPriceById(purchPriceId:Int): PurchasesPrices?

    @Update
    suspend fun updatePurchPrice(purchPrice: PurchasesPrices)

    @Delete
    suspend fun removePurchPrice(purchPrice: PurchasesPrices)

    @Query("DELETE FROM ${PurchasesPricesContract.TABLE_NAME} WHERE ${PurchasesPricesContract.Columns.ID}=:purchPriceId")
    suspend fun removePurchPriceById(purchPriceId: Int)


}