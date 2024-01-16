package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.Provider
import com.example.roomdao30.AllEntitys.Supply
import com.example.roomdao30.DB.Database

class SupplyRepository {
    private val supplyDao = Database.INSTANCE!!.supplyDao()

    suspend fun saveSupply(supply:Supply) {
        supplyDao.insertSupply(supply)
    }

    suspend
    fun updateSupply(supply:Supply) {
        supplyDao.updateSupply(supply)
    }

    suspend fun removeSupply(supply: Supply) {
        supplyDao.removeSupply(supply)
    }

    suspend fun removeSupplyById(supId: Int) {
       supplyDao.removeSupplyById(supId)
    }

    suspend fun getSupplyById(supId: Int): Supply? {
        return supplyDao.getSupplyById(supId)

    }

    suspend fun getAllSupplys(): List<Supply> {
        return supplyDao.getAllSupplys()
    }
}