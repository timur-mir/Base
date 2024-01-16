package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Employee
import com.example.roomdao30.AllRelation.EmployeeAndProductsRelation
import com.example.roomdao30.DB.Database

class EmployeeRepository {
    private val employeeDao = Database.INSTANCE!!.employeeDao()
    suspend fun getAllEmployeeAndProductsWithRelations(employeeId:Int):EmployeeAndProductsRelation{
        return  employeeDao.getAllEmployeeAndProductsWithRelations(employeeId)
    }

    suspend fun saveEmpl(employee:Employee) {
        // if(isClientValid(client).not()) throw IncorrectFormException()
        employeeDao.insertEmployee(employee)
            }

    suspend fun updateEmployee(employee: Employee) {
        // if(isClientValid(client).not()) throw IncorrectFormException()
        employeeDao.updateEmployee(employee)

    }

    suspend fun removeEmployee(employee: Employee) {
        employeeDao.removeEmployee(employee)
    }
    suspend fun removeEmployeeById(emplId:Int) {
        employeeDao.removeEmployeeById(emplId)
    }

    suspend fun getEmployeeById(emplId:Int): Employee? {
        val result =employeeDao.getEmployeeById(emplId)
        return result

    }

    suspend fun getAllEmployees(): List<Employee> {
        return employeeDao.getAllEmployees()
    }

}