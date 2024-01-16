package com.example.roomdao30.AllRelation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Employee
import com.example.roomdao30.AllEntitys.OfficialCar

data class EmployeeWithCar (
    @Embedded
    val employee: Employee,
    @Relation(
        parentColumn = "id", entityColumn = "id")
    val car: OfficialCar
)