package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.PurchasesContract
import com.example.roomdao30.AllEntitys.Purchases

@Dao
interface PurchasesAlt {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPurchasesAlt(purchase:Purchases)

  @Query("SELECT*FROM ${PurchasesContract.TABLE_NAME}")
  suspend fun getAllPurchasesAlt():List<Purchases>

  @Update
  suspend fun updatePurchaseAlt(purchase:Purchases)

  @Query("SELECT*FROM ${PurchasesContract.TABLE_NAME} WHERE ${PurchasesContract.Columns.ID}=:purchaseIdAlt")
    suspend fun getPurchaseByIdAlt(purchaseIdAlt:Int):Purchases?

    @Delete
    suspend fun removePurchaseAlt(purchase: Purchases)

    @Query("DELETE FROM ${PurchasesContract.TABLE_NAME} WHERE ${PurchasesContract.Columns.ID}=:purchaseIdAlt")
          fun  removePurchaseByIdAlt(purchaseIdAlt: Int)
}