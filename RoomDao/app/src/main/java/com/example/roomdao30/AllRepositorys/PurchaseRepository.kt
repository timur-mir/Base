package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.Purchases

import com.example.roomdao30.DB.Database

class PurchaseRepository {
    private val purchaseDao = Database.INSTANCE!!.purchasesDao()

    suspend fun savePurchase(purchase: Purchases) {
        purchaseDao.insertPurchasesAlt(purchase)
    }

    suspend
    fun updatePurchase(purchase: Purchases) {
        purchaseDao.updatePurchaseAlt(purchase)
    }

    suspend fun removePurchase(purchase: Purchases) {
        purchaseDao.removePurchaseAlt(purchase)
    }

    suspend fun removePurchaseById(purchaseId: Int) {
        purchaseDao.removePurchaseByIdAlt(purchaseId)
    }

    suspend fun getPurchaseById(purchaseId: Int): Purchases? {
        return purchaseDao.getPurchaseByIdAlt(purchaseId)

    }

    suspend fun getAllPurchases(): List<Purchases> {
        return purchaseDao.getAllPurchasesAlt()
    }
}