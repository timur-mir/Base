package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Prices
import com.example.roomdao30.DB.Database

class PriceRepository {
    private val priceDao = Database.INSTANCE!!.pricesDao()
    suspend fun savePrice(price: Prices) {
        priceDao.insertPrice(price)
    }

    suspend
    fun updatePrice(price: Prices) {
        priceDao.updatePrice(price)
    }

    suspend fun removePrice(price: Prices) {
        priceDao.removePrice(price)
    }

    suspend fun removePriceById(priceId: Int) {
        priceDao.removePriceById(priceId)
    }

    suspend fun getPriceById(priceId: Int): Prices? {
        return priceDao.getPriceById(priceId)

    }

    suspend fun getAllPrices(): List<Prices> {
        return priceDao.getAllPricesBase()
    }
}