package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.ClientsContract
import com.example.roomdao30.AllContracts.ProvidersContract
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Provider
import com.example.roomdao30.AllEntitys.Supply
import com.example.roomdao30.AllRelation.ProvidersAndProductsRelation

@Dao
interface ProviderDao {
    @Transaction
    @Query("SELECT*FROM ${ProvidersContract.TABLE_NAME} WHERE id_prov =:providersId")
    suspend fun getProviderAndProductsWithRelations(providersId:Int): ProvidersAndProductsRelation
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertProvider(provider:Provider)

    @Query("SELECT*FROM ${ProvidersContract.TABLE_NAME}")
    suspend fun getAllProviders():List<Provider>

    @Query("SELECT*FROM ${ProvidersContract.TABLE_NAME} WHERE ${ProvidersContract.Columns.ID_PROV}=:provId")
    suspend fun getProviderById(provId:Int): Provider?

    @Update
    suspend fun updateProvider(provider: Provider)

    @Delete
    suspend fun removeProvider(provider: Provider)

    @Query("DELETE FROM ${ProvidersContract.TABLE_NAME} WHERE ${ProvidersContract.Columns.ID_PROV}=:provId")
    suspend fun removeProviderById(provId: Int)
}