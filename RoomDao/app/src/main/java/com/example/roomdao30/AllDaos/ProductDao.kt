package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllEntitys.Product
import com.example.roomdao30.AllRelation.ProductWithRating

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT*FROM products")
    suspend fun getAllProductsWithRelations():List<ProductWithRating>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT*FROM products")
    suspend fun getAllProducts():List<Product>

    @Query("SELECT*FROM products WHERE id_pr=:productId")
    suspend fun getProductById(productId:Int): Product?

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun removeProduct(product: Product)

    @Query("DELETE FROM products WHERE id_pr =:productId")
    suspend fun removeProductById(productId:Int)


}