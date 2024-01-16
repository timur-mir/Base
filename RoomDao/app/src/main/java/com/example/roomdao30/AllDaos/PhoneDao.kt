package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.PhoneNumberContract
import com.example.roomdao30.AllContracts.PricesContract
import com.example.roomdao30.AllEntitys.PhoneNumber
import com.example.roomdao30.AllEntitys.Prices

@Dao
interface PhoneDao {
    @Insert
    suspend fun insertPhone(phoneNumber: PhoneNumber)

    @Query("SELECT*FROM ${PhoneNumberContract.TABLE_NAME}")
    suspend fun getAllPhones():List<PhoneNumber>

    @Query("SELECT*FROM ${PhoneNumberContract.TABLE_NAME} WHERE ${PhoneNumberContract.Columns.LOGIN}=:loginclt")
    suspend fun getPhoneByLogin(loginclt:String): PhoneNumber?

    @Update
    suspend fun updatePhone(phoneNumber: PhoneNumber)

    @Query("DELETE FROM ${PhoneNumberContract.TABLE_NAME}")
    suspend fun removeAllPhoneNumbers()

    @Query("DELETE FROM ${PhoneNumberContract.TABLE_NAME} WHERE ${PhoneNumberContract.Columns.LOGIN}=:loginclt")
    suspend fun removePhoneByLogin(loginclt:String)

}