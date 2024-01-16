package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.EmployeesContract
import com.example.roomdao30.AllContracts.OfficialCarContract
import com.example.roomdao30.AllEntitys.Employee
import com.example.roomdao30.AllEntitys.OfficialCar

@Dao
interface OfficialCarDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertCar(car:OfficialCar)

    @Query("SELECT*FROM ${OfficialCarContract.TABLE_NAME}")
    suspend fun getAllCars():List<OfficialCar>

    @Query("SELECT*FROM ${OfficialCarContract.TABLE_NAME} WHERE ${OfficialCarContract.Columns.ID}=:carId")
    suspend fun getCarById(carId:Int): OfficialCar?

    @Update
    suspend fun updateCar(car: OfficialCar)

    @Delete
    suspend fun removeCar(car: OfficialCar)

    @Query("DELETE FROM ${OfficialCarContract.TABLE_NAME} WHERE ${OfficialCarContract.Columns.ID}=:carId")
    suspend fun removeCarById(carId: Int)
}