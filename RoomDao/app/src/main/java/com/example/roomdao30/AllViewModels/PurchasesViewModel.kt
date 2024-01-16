package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.Purchases
import com.example.roomdao30.AllRepositorys.PurchaseRepository
import com.example.roomdao30.AllRepositorys.PurchasesPricesRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class PurchasesViewModel:ViewModel() {
    private val purchaseRepository = PurchaseRepository()

    private val getPurchaseMutableLiveData = MutableLiveData<Purchases?>()
    val updatePurchaseLiveData: LiveData<Purchases?>
        get() = getPurchaseMutableLiveData

    private val purchaseMutableLiveData = MutableLiveData<List<Purchases>>()
    val recordPurchasesData: LiveData<List<Purchases>>
        get() = purchaseMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent

    fun loadList() {
        viewModelScope.launch {
            try {
                purchaseMutableLiveData.postValue(purchaseRepository.getAllPurchases())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке покупки ")
            }
        }
    }

    fun removePurchaseById(purchase: Int) {
        viewModelScope.launch {
            try {
                purchaseRepository.removePurchaseById(purchase)
            } catch (t: Throwable) {
                Timber.e(t)
            }

        }
    }

    fun getPurchaseById(id: Int) {
        viewModelScope.launch {
            try {
                val purchase= purchaseRepository.getPurchaseById(id)
                getPurchaseMutableLiveData.postValue(purchase)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun save(purchase: Purchases) {
        viewModelScope.launch {
            try {
                if (purchase.id == 0) {
                    purchaseRepository.savePurchase(purchase)
                } else {
                    purchaseRepository.updatePurchase(purchase)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения покупки")
                showError(t)
            }
        }
    }

    fun updatePurchase(purchase: Purchases) {
        viewModelScope.launch {
            try {
                purchaseRepository.updatePurchase(purchase)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления покупки")
                showError(t)

            }

        }
    }

    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(1550)
    }

}