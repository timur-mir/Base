package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.Prices
import com.example.roomdao30.AllRepositorys.PriceRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class PriceViewModel:ViewModel() {
    private val priceRepository = PriceRepository()

    private val getPriceMutableLiveData = MutableLiveData<Prices?>()
    val updatePriceLiveData: LiveData<Prices?>
        get() = getPriceMutableLiveData

    private val pricesMutableLiveData = MutableLiveData<List<Prices>>()
    val allPricesData: LiveData<List<Prices>>
        get() = pricesMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent



    fun loadList() {
        viewModelScope.launch {
            try {
                pricesMutableLiveData.postValue(priceRepository.getAllPrices())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке прайсов ")
            }
        }
    }

    fun removePriceById(price: Int) {
        viewModelScope.launch {
            try {
                priceRepository.removePriceById(price)
            } catch (t: Throwable) {
                Timber.e(t)
            }

        }
    }

    fun getPriceById(id: Int) {
        viewModelScope.launch {
            try {
                val price= priceRepository.getPriceById(id)
                getPriceMutableLiveData.postValue(price)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun save(price: Prices) {
        viewModelScope.launch {
            try {
                if (price.id == 0) {
                    priceRepository.savePrice(price)
                } else {
                    priceRepository.updatePrice(price)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения прайса")
                showError(t)
            }
        }
    }

    fun updatePrice(price: Prices) {
        viewModelScope.launch {
            try {
                priceRepository.updatePrice(price)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления прайса")
                showError(t)

            }

        }
    }

    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(2777)
    }
}