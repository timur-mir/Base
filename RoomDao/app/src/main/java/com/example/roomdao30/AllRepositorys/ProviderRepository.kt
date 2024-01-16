package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.OfficialCar
import com.example.roomdao30.AllEntitys.Provider
import com.example.roomdao30.AllRelation.EmployeeAndProductsRelation
import com.example.roomdao30.AllRelation.ProvidersAndProductsRelation
import com.example.roomdao30.DB.Database

class ProviderRepository {
    private val providerDao = Database.INSTANCE!!.providerDao()
    suspend fun getAllProviderAndProductsWithRelations(providerId:Int): ProvidersAndProductsRelation{
        return  providerDao.getProviderAndProductsWithRelations(providerId)
    }
    suspend fun saveProvider(provider: Provider) {
       providerDao.insertProvider(provider)
    }

    suspend
    fun updateProvider(provider: Provider) {
       providerDao.updateProvider(provider)
    }

    suspend fun removeProvider(provider: Provider) {
        providerDao.removeProvider(provider)
    }

    suspend fun removeProviderById(provId: Int) {
        providerDao.removeProviderById(provId)
    }

    suspend fun getProviderById(provId: Int): Provider? {
        return providerDao.getProviderById(provId)

    }

    suspend fun getAllProviders(): List<Provider> {
        return providerDao.getAllProviders()
    }

}