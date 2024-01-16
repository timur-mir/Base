package com.example.roomdao30.AllEntitys

import androidx.room.*
import com.example.roomdao30.AllContracts.ClientsContract
import com.example.roomdao30.AllContracts.RatingContract

@Entity(
    tableName = RatingContract.TABLE_NAME,

    foreignKeys = [
        ForeignKey(
           entity = Client::class,
           parentColumns = [ClientsContract.Columns.LOGIN],
           childColumns = [RatingContract.Columns.LOGIN],
          onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
//        )])
       ForeignKey( entity = Product::class,
           parentColumns = ["id_pr"],
           childColumns = [RatingContract.Columns.ID_PRODUCT],
               onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),

        ForeignKey( entity = BookShop::class,
            parentColumns = ["id_shop"],
            childColumns = [RatingContract.Columns.ID_SHOP],
            onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)

    ]



)



data class Rating(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RatingContract.Columns.ID)
    val id: Int,
    @ColumnInfo(name = RatingContract.Columns.LOGIN, index = true)
    val login: String,
    @ColumnInfo(name = RatingContract.Columns.ID_SHOP, index = true)
    val id_shop: Int,
    @ColumnInfo(name = RatingContract.Columns.ID_PRODUCT, index = true)
    val id_product: Int,
    @ColumnInfo(name = RatingContract.Columns.TITLE)
    val title: String?,
    @ColumnInfo(name = RatingContract.Columns.DESCRIPTION)
    val description: String?,
    @ColumnInfo(name = RatingContract.Columns.STARS)
    val stars: String
)
