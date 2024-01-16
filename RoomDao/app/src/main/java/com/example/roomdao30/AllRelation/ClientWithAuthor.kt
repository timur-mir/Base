package com.example.roomdao30.AllRelation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Client

data class ClientWithAuthor(
  @Embedded val client:Client,
    @Relation(
        parentColumn = "id", entityColumn = "id")
    val author: Author
)

