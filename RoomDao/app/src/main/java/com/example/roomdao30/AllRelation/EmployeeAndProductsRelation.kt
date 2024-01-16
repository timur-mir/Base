package com.example.roomdao30.AllRelation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomdao30.AllContracts.SaleContract
import com.example.roomdao30.AllEntitys.Employee
import com.example.roomdao30.AllEntitys.Product
import com.example.roomdao30.AllEntitys.Sale

data class EmployeeAndProductsRelation(
    @Embedded val employee: Employee,
    @Relation(
        parentColumn = "id",
        entityColumn = "id_pr",
        associateBy = Junction(
            value= Sale::class,
            parentColumn = "${SaleContract.Columns.ID_EMPLOYEE}",
            entityColumn = "${SaleContract.Columns.ID_PRODUCT}"
        )

    )
val products:List<Product>
)
