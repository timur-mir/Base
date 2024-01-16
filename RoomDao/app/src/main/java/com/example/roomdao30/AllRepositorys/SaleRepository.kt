package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.Rating
import com.example.roomdao30.AllEntitys.Sale
import com.example.roomdao30.DB.Database

class SaleRepository {
    private val saleDao = Database.INSTANCE!!.saleDao()

    suspend fun saveSale(sale: Sale) {
       saleDao.insertSale(sale)
    }

    suspend fun updateSale(sale: Sale) {
        saleDao.updateSale(sale)
    }

    suspend fun removeSale(sale: Sale) {
        saleDao.removeSale(sale)
    }

    suspend fun removeSaleById(saleId: Int) {
       saleDao.removeSaleById(saleId)
    }
    suspend fun getSaleById(saleId: Int): Sale? {
        return saleDao.getSaleById(saleId)
    }

    suspend fun getAllSales(): List<Sale> {
        return saleDao.getAllSales()
    }


//    suspend fun requestRating():List<ClientWithRating> {
//        return clientDao.getAllClientWithRelations()
//    }

}