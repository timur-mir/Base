package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.Rating
import com.example.roomdao30.AllRelation.ClientWithRating
import com.example.roomdao30.DB.Database

class RatingRepository {
    private val ratingDao = Database.INSTANCE!!.ratingDao()

    private val clientDao = Database.INSTANCE!!.clientDao()

    suspend fun saveRating(rating: Rating) {
             ratingDao.insertRating(rating)
    }

    suspend
    fun updateRating(rating: Rating) {
       ratingDao.updateRating(rating)
    }
    suspend
    fun requestRating():List<ClientWithRating> {
        return clientDao.getAllClientWithRelations()
    }


    suspend fun removeRating(ratingId: Int) {

    }
    suspend fun removeRatingById(ratingId: Int) {
       ratingDao.removeRatingById(ratingId)
    }

    suspend fun getRatingById(rating: Int): Rating? {
        val result =ratingDao.getRatingById(rating)
        return result
    }

    suspend fun getAllRatings(): List<Rating> {
        return ratingDao.getAllRatings()
    }
}