package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.PurchaseStatuse
import com.example.roomdao30.DB.Database

class PurchaseStatuseRepository {
    private val purchaseStDao = Database.INSTANCE!!.purchaseStatuseDao()

    suspend fun savePurchaseSt(purchaseSt: PurchaseStatuse) {
        purchaseStDao.insertPurchaseSt(purchaseSt)
    }

    suspend
    fun updatePurchaseSt(purchaseSt: PurchaseStatuse) {
        purchaseStDao.updatePurchaseSt(purchaseSt)
    }

    suspend fun removePurchaseSt(purchaseSt: PurchaseStatuse) {
        purchaseStDao.removePurchaseSt(purchaseSt)
    }

    suspend fun removePurchaseStById(purchaseSt: Int) {
        purchaseStDao.removePurchaseStById(purchaseSt)
    }

    suspend fun getPurchaseStById(purchaseSt: Int): PurchaseStatuse? {
        return purchaseStDao.getPurchaseStById(purchaseSt)

    }

    suspend fun getAllPurchaseSts(): List<PurchaseStatuse> {
        return purchaseStDao.getAllPurchasesSt()
    }
}