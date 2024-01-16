package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.ClientsContract
import com.example.roomdao30.AllContracts.EmployeesContract
import com.example.roomdao30.AllContracts.ProvidersContract
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Employee
import com.example.roomdao30.AllRelation.ClientWithRating
import com.example.roomdao30.AllRelation.EmployeeAndProductsRelation
import com.example.roomdao30.AllRelation.EmployeeWithCar
import com.example.roomdao30.AllRelation.ProvidersAndProductsRelation

@Dao
interface EmployeeDao {

    @Transaction
    @Query("SELECT*FROM ${EmployeesContract.TABLE_NAME} WHERE id=:employeeId")
    suspend fun getAllEmployeeAndProductsWithRelations(employeeId:Int):EmployeeAndProductsRelation
    @Transaction
    @Query("SELECT*FROM ${EmployeesContract.TABLE_NAME}")
    suspend fun getAllCarWithRelations():List<EmployeeWithCar>
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Query("SELECT*FROM ${EmployeesContract.TABLE_NAME}")
    suspend fun getAllEmployees():List<Employee>

    @Query("SELECT*FROM ${EmployeesContract.TABLE_NAME} WHERE ${EmployeesContract.Columns.ID}=:emplId")
    suspend fun getEmployeeById(emplId:Int): Employee?

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun removeEmployee(employee: Employee)

    @Query("DELETE FROM ${EmployeesContract.TABLE_NAME} WHERE ${EmployeesContract.Columns.ID}=:emplId")
    suspend fun removeEmployeeById(emplId: Int)
}