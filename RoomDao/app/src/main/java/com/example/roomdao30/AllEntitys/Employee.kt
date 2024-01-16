package com.example.roomdao30.AllEntitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName ="employees",indices = [
    Index("email", unique = true)
])
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String?,
    val post :String
)
