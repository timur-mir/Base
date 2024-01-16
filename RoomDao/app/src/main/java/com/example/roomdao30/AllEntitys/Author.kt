package com.example.roomdao30.AllEntitys

import androidx.room.*
import com.example.roomdao30.AllContracts.AuthorsContract
import com.example.roomdao30.AllContracts.ClientsContract
import com.example.roomdao30.AllContracts.ClientsContract.TABLE_NAME
import com.example.roomdao30.AllContracts.RatingContract

@Entity(tableName = AuthorsContract.TABLE_NAME)

data class Author(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = AuthorsContract.Columns.ID)
    val id: Int,
    @ColumnInfo(name = AuthorsContract.Columns.AUTHOR_NAME)
    val nameAuthor: String
)
