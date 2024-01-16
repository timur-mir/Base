package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.ClientsContract
import com.example.roomdao30.AllContracts.PhoneNumberContract
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.AllRelation.ClientWithRating


@Dao
interface ClientDao {
    @Transaction
    @Query("SELECT*FROM ${ClientsContract.TABLE_NAME}")
    @RewriteQueriesToDropUnusedColumns
    suspend fun getAllClientWithRelations():List<ClientWithRating>
    @Transaction
    @Query("SELECT*FROM ${ClientsContract.TABLE_NAME}")
    suspend fun getAllAuthorsWithRelations():List<ClientWithAuthor>

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertClient(client: Client)

    @Query("SELECT*FROM ${ClientsContract.TABLE_NAME}")
    suspend fun getAllClients():List<Client>

    @Query("SELECT*FROM ${ClientsContract.TABLE_NAME} WHERE ${ClientsContract.Columns.ID}=:clientId")
    suspend fun getClientById(clientId:Int):Client?
    @Transaction
    @Update
    suspend fun updateClient(client: Client)

    @Delete
    suspend fun removeClient(client: Client)

    @Query("DELETE FROM ${ClientsContract.TABLE_NAME} WHERE ${ClientsContract.Columns.ID}=:clientId")
    suspend fun removeClientById(clientId: Int)

    @Query("DELETE FROM ${ClientsContract.TABLE_NAME}")
    suspend fun removeAllClients()

}