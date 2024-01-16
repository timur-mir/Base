package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.BookShop
import com.example.roomdao30.DB.Database

class BookShopRepository {
   private val booksShopDao = Database.INSTANCE!!.shopDao()

    suspend fun saveShop(shop: BookShop) {
        booksShopDao.insertShop(shop)
    }

    suspend
    fun updateShop(shop: BookShop) {
        booksShopDao.updateShop(shop)
    }
    suspend fun getAllShop(): List<BookShop> {
        return booksShopDao.getAllShop()
    }

//    suspend
//    fun requestProducts():List<ProductWithRating> {
//        return productDao.getAllProductsWithRelations()
//    }

}