package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.PhoneNumber
import com.example.roomdao30.DB.Database

class PhoneNumberRepository {
    private val phoneDao = Database.INSTANCE!!.phoneNumberDao()

    suspend fun savePhone(phoneNumber: PhoneNumber) {
        phoneDao.insertPhone(phoneNumber)
    }

    suspend
    fun updatePhone(phoneNumber: PhoneNumber) {
        phoneDao.updatePhone(phoneNumber)
    }

    suspend fun removeAllPhoneNumber() {
        phoneDao.removeAllPhoneNumbers()
    }

    suspend fun removePhoneByLogin(loginClient:String) {
       phoneDao.removePhoneByLogin(loginClient)
    }

    suspend fun getPhoneByLogin(loginClient:String):PhoneNumber? {
        return phoneDao.getPhoneByLogin(loginClient)

    }

    suspend fun getAllPhones(): List<PhoneNumber> {
        return phoneDao.getAllPhones()
    }

}