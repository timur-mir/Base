package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.AuthorsContract
import com.example.roomdao30.AllContracts.PricesContract
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Prices

@Dao
interface  PricesDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertPrice(price: Prices)

    @Query("SELECT*FROM ${PricesContract.TABLE_NAME}")
    suspend fun getAllPricesBase():List<Prices>

    @Query("SELECT*FROM ${PricesContract.TABLE_NAME} WHERE ${PricesContract.Columns.ID}=:priceId")
    suspend fun getPriceById(priceId:Int): Prices?

    @Update
    suspend fun updatePrice(price:Prices)

    @Delete
    suspend fun removePrice(price: Prices)

    @Query("DELETE FROM ${PricesContract.TABLE_NAME} WHERE ${PricesContract.Columns.ID}=:priceId")
    suspend fun removePriceById(priceId: Int)

}