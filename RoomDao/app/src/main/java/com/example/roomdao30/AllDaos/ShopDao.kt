package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllEntitys.BookShop

@Dao
interface ShopDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertShop(shop: BookShop)

    @Query("SELECT*FROM bookshop")
    suspend fun getAllShop():List<BookShop>

    @Query("SELECT*FROM bookshop WHERE id_shop =:shopId")
    suspend fun getShopById(shopId:Int): BookShop?

    @Update
    suspend fun updateShop(shop: BookShop)

    @Delete
    suspend fun removeShop(shop: BookShop)

    @Query("DELETE FROM bookshop WHERE id_shop =:shopId")
    suspend fun removeShopById(shopId:Int)
}