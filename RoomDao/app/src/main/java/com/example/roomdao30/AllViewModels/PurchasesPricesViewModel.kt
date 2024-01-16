package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.roomdao30.AllEntitys.PurchasesPrices
import com.example.roomdao30.AllRepositorys.PurchasesPricesRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class PurchasesPricesViewModel:ViewModel() {
    private val purchPriceRepository = PurchasesPricesRepository()

    private val getPurchPricesMutableLiveData = MutableLiveData<PurchasesPrices?>()
    val updatePurchasesPricesLiveData: LiveData<PurchasesPrices?>
        get() = getPurchPricesMutableLiveData

    private val purchPricesMutableLiveData = MutableLiveData<List<PurchasesPrices>>()
    val recordPurchasesPricesData: LiveData<List<PurchasesPrices>>
        get() = purchPricesMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent

    fun loadList() {
        viewModelScope.launch {
            try {
                purchPricesMutableLiveData.postValue(purchPriceRepository.getAllPurchPrices())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке покупки/прайсы ")
            }
        }
    }

    fun removePurchPriceById(purchPrice: Int) {
        viewModelScope.launch {
            try {
               purchPriceRepository.removePurchPriceById(purchPrice)
            } catch (t: Throwable) {
                Timber.e(t)
            }

        }
    }

    fun getPurchPriceById(id: Int) {
        viewModelScope.launch {
            try {
                val purchPrice= purchPriceRepository.getPurchPriceById(id)
                getPurchPricesMutableLiveData.postValue(purchPrice)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun save(purchPrice: PurchasesPrices) {
        viewModelScope.launch {
            try {
                if (purchPrice.id == 0) {
                  purchPriceRepository.savePurchPrice(purchPrice)
                } else {
                    purchPriceRepository.updatePurchPrice(purchPrice)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения прайса/покупки")
                showError(t)
            }
        }
    }

    fun updatePurchPrice(purchPrice: PurchasesPrices) {
        viewModelScope.launch {
            try {
                purchPriceRepository.updatePurchPrice(purchPrice)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления покупки/прайса")
                showError(t)

            }

        }
    }

    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(1350)
    }

}