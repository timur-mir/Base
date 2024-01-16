package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.Rating
import com.example.roomdao30.AllRelation.ClientWithRating
import com.example.roomdao30.AllRepositorys.RatingRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class RatingViewModel : ViewModel() {
    private val ratingRepository = RatingRepository()

    private val getRatingMutableLiveData = MutableLiveData<Rating?>()

    val updateRatingLiveData: LiveData<Rating?>
        get() = getRatingMutableLiveData

    private val requestRatingsForClient = MutableLiveData<List<ClientWithRating>>()

    val requestRatingList: LiveData<List<ClientWithRating>>
        get() = requestRatingsForClient


    private val ratingsMutableLiveData = MutableLiveData<List<Rating>>()

    val recordRatingData: LiveData<List<Rating>>
        get() = ratingsMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent


    fun requestRating() {
        viewModelScope.launch {
            try {
                requestRatingsForClient.postValue(ratingRepository.requestRating())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в запросе рейтинга товаров $t ")
            }
        }

    }

    fun loadList() {
        viewModelScope.launch {
            try {
                ratingsMutableLiveData.postValue(ratingRepository.getAllRatings())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке рейтинга ")
            }
        }
    }

    fun removeRatingById(rating: Int) {
        viewModelScope.launch {
            try {
                ratingRepository.removeRatingById(rating)
            } catch (t: Throwable) {
                Timber.e(t)
            }

        }
    }

    fun getRatingClientById(id: Int) {
        viewModelScope.launch {
            try {
                val rating = ratingRepository.getRatingById(id)
                getRatingMutableLiveData.postValue(rating)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun save(rating: Rating) {
        viewModelScope.launch {
            try {
                if (rating.id == 0) {
                    ratingRepository.saveRating(rating)
                } else {
                    ratingRepository.updateRating(rating)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения рейтинга")
                showError(t)
            }
        }
    }

    fun updateRating(rating: Rating) {
        viewModelScope.launch {
            try {
                ratingRepository.updateRating(rating)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления рейтинга")
                showError(t)

            }

        }
    }

    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(2150)
    }


}


