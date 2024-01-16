package com.example.roomdao30.AllRelation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomdao30.AllContracts.RatingContract
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Product
import com.example.roomdao30.AllEntitys.Rating

data class ProductWithRating (
    @Embedded val product: Product,
    @Relation(
        parentColumn = "id_pr", entityColumn = RatingContract.Columns.ID_PRODUCT)
    val rating: List<Rating>
        )