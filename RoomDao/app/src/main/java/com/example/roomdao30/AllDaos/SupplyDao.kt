package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.ProvidersContract
import com.example.roomdao30.AllContracts.SupplyContract
import com.example.roomdao30.AllEntitys.Provider
import com.example.roomdao30.AllEntitys.Supply
@Dao
interface SupplyDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertSupply(supply: Supply)

    @Query("SELECT*FROM ${SupplyContract.TABLE_NAME}")
    suspend fun getAllSupplys():List<Supply>

    @Query("SELECT*FROM ${SupplyContract.TABLE_NAME} WHERE ${SupplyContract.Columns.ID_SUP}=:supId")
    suspend fun getSupplyById(supId:Int): Supply?

    @Update
    suspend fun updateSupply(supply: Supply)

    @Delete
    suspend fun removeSupply(supply: Supply)

    @Query("DELETE FROM ${SupplyContract.TABLE_NAME} WHERE ${SupplyContract.Columns.ID_SUP}=:supId")
    suspend fun removeSupplyById(supId: Int)
}