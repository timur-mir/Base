package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Product
import com.example.roomdao30.AllRelation.ProductWithRating
import com.example.roomdao30.DB.Database

class ProductRepository {
    private val productDao = Database.INSTANCE!!.productDao()

    suspend fun saveProduct(product: Product) {
       productDao.insertProduct(product)
    }

    suspend
    fun updateProduct(product: Product) {
        productDao.updateProduct(product)
    }
    suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }

    suspend
    fun requestProducts():List<ProductWithRating> {
        return productDao.getAllProductsWithRelations()
    }
}