package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.PurchasesContract
import com.example.roomdao30.AllContracts.PurchasesStatuseContract
import com.example.roomdao30.AllEntitys.PurchaseStatuse
import com.example.roomdao30.AllEntitys.Purchases

@Dao
interface PurchasesStatusDao {

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertPurchaseSt(purchaseSt: PurchaseStatuse)

    @Query("SELECT*FROM ${PurchasesStatuseContract.TABLE_NAME}")
    suspend fun getAllPurchasesSt():List<PurchaseStatuse>

    @Query("SELECT*FROM ${PurchasesStatuseContract.TABLE_NAME} WHERE ${PurchasesStatuseContract.Columns.ID}=:purchaseStId")
    suspend fun getPurchaseStById(purchaseStId:Int): PurchaseStatuse?

    @Update
    suspend fun updatePurchaseSt(purchaseSt: PurchaseStatuse)

    @Delete
    suspend fun removePurchaseSt(purchaseSt: PurchaseStatuse)

    @Query("DELETE FROM ${PurchasesStatuseContract.TABLE_NAME} WHERE ${PurchasesStatuseContract.Columns.ID}=:purchaseStId")
    suspend fun removePurchaseStById(purchaseStId: Int)
}