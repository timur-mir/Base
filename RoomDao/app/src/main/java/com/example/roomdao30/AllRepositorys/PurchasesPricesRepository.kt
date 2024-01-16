package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.Prices
import com.example.roomdao30.AllEntitys.PurchasesPrices
import com.example.roomdao30.DB.Database

class PurchasesPricesRepository {
    private val purchPriceDao = Database.INSTANCE!!.purchasesPricesDao()

    suspend fun savePurchPrice(purchprice: PurchasesPrices) {
        purchPriceDao.insertPurchPrice(purchprice)
    }

    suspend
    fun updatePurchPrice(purchPrice: PurchasesPrices) {
       purchPriceDao.updatePurchPrice(purchPrice)
    }

    suspend fun removePurchPrice(purchPrice: PurchasesPrices) {
        purchPriceDao.removePurchPrice(purchPrice)
    }

    suspend fun removePurchPriceById(purchPriceId: Int) {
        purchPriceDao.removePurchPriceById(purchPriceId)
    }

    suspend fun getPurchPriceById(purchPriceId: Int): PurchasesPrices? {
        return purchPriceDao.getPurchPriceById(purchPriceId)

    }

    suspend fun getAllPurchPrices(): List<PurchasesPrices> {
        return purchPriceDao.getAllPurchPricesBase()
    }
}