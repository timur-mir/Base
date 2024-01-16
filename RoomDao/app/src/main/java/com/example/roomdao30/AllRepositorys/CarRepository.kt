package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.OfficialCar
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.AllRelation.EmployeeWithCar
import com.example.roomdao30.DB.Database

class CarRepository {
    private val carDao = Database.INSTANCE!!.carDao()
    private val employeeDao = Database.INSTANCE!!.employeeDao()

    suspend
    fun requestCar():List<EmployeeWithCar> {
        return employeeDao.getAllCarWithRelations()
    }


    suspend fun saveCar(car:OfficialCar) {
        carDao.insertCar(car)
    }

    suspend
    fun updateCar(car: OfficialCar) {
        carDao.updateCar(car)
    }

    suspend fun removeCar(car: OfficialCar) {
        carDao.removeCar(car)
    }

    suspend fun removeCarById(carId: Int) {
        carDao.removeCarById(carId)
    }

    suspend fun getCarById(carId: Int): OfficialCar? {
        return carDao.getCarById(carId)

    }

    suspend fun getAllCars(): List<OfficialCar> {
        return carDao.getAllCars()
    }
}