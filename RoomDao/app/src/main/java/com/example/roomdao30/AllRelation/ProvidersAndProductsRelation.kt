package com.example.roomdao30.AllRelation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomdao30.AllContracts.ProvidersContract
import com.example.roomdao30.AllContracts.SaleContract
import com.example.roomdao30.AllContracts.SupplyContract
import com.example.roomdao30.AllEntitys.*

data class ProvidersAndProductsRelation(
    @Embedded
    val provider: Provider,
    @Relation(
        parentColumn = "${ProvidersContract.Columns.ID_PROV}",
        entityColumn = "id_pr",
        associateBy = Junction(
            value= Supply::class,
            parentColumn = "${SupplyContract.Columns.ID_PROVIDER}",
            entityColumn = "${SupplyContract.Columns.ID_PRODUCT}"
        )
    )
    val products:List<Product>
    )
