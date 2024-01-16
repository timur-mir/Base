package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.AuthorsContract
import com.example.roomdao30.AllEntitys.Author


@Dao
interface  AuthorDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertAuthor(author: Author)

    @Query("SELECT*FROM ${AuthorsContract.TABLE_NAME}")
    suspend fun getAllAuthors():List<Author>

    @Query("SELECT*FROM ${AuthorsContract.TABLE_NAME} WHERE ${AuthorsContract.Columns.ID}=:authorId")
    suspend fun getAuthorById(authorId:Int): Author?

    @Update
    suspend fun updateAuthor(author: Author)

    @Delete
    suspend fun removeAuthor(author: Author)

    @Query("DELETE FROM ${AuthorsContract.TABLE_NAME} WHERE ${AuthorsContract.Columns.ID}=:authorId")
    suspend fun removeAuthorById(authorId: Int)

}