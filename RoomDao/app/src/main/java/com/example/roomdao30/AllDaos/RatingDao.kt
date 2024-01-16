package com.example.roomdao30.AllDaos

import androidx.room.*
import com.example.roomdao30.AllContracts.ClientsContract
import com.example.roomdao30.AllContracts.RatingContract
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Rating

@Dao
interface RatingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRating(rating:Rating)

    @Query("SELECT*FROM ${RatingContract.TABLE_NAME}")
    suspend fun getAllRatings():List<Rating>

    @Query("SELECT*FROM ${RatingContract.TABLE_NAME} WHERE ${RatingContract.Columns.ID}=:ratingId")
    suspend fun getRatingById(ratingId:Int): Rating?

    @Update
    suspend fun updateRating(rating: Rating)

    @Delete
    suspend fun removeRating(rating: Rating)

    @Query("DELETE FROM ${RatingContract.TABLE_NAME} WHERE ${RatingContract.Columns.ID}=:ratingId")
    suspend fun removeRatingById(ratingId: Int)

}