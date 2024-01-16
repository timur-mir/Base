package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.PurchaseStatuse

import com.example.roomdao30.AllRepositorys.PurchaseStatuseRepository
import com.example.roomdao30.AllRepositorys.PurchasesPricesRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class PurchasesStatusViewModel : ViewModel() {
    private val purchaseStRepository = PurchaseStatuseRepository()

    private val getPurchaseStatuseMutableLiveData = MutableLiveData<PurchaseStatuse?>()
    val updatePurchasesStatuseLiveData: LiveData<PurchaseStatuse?>
        get() = getPurchaseStatuseMutableLiveData

    private val purchaseStsMutableLiveData = MutableLiveData<List<PurchaseStatuse>>()
    val recordPurchasesStatuseData: LiveData<List<PurchaseStatuse>>
        get() = purchaseStsMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent

    fun loadList() {
        viewModelScope.launch {
            try {
                purchaseStsMutableLiveData.postValue(purchaseStRepository.getAllPurchaseSts())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке статуса ")
            }
        }
    }

    fun removePurchaseStatuseById(purchaseSt: Int) {
        viewModelScope.launch {
            try {
                purchaseStRepository.removePurchaseStById(purchaseSt)
            } catch (t: Throwable) {
                Timber.e(t)
            }

        }
    }

    fun getPurchaseStatuseById(id: Int) {
        viewModelScope.launch {
            try {
                val purchaseSt= purchaseStRepository.getPurchaseStById(id)
                getPurchaseStatuseMutableLiveData.postValue(purchaseSt)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun save(purchaseSt: PurchaseStatuse) {
        viewModelScope.launch {
            try {
                if (purchaseSt.id == 0) {
                    purchaseStRepository.savePurchaseSt(purchaseSt)
                } else {
                    purchaseStRepository.updatePurchaseSt(purchaseSt)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения статуса")
                showError(t)
            }
        }
    }

    fun updatePurchaseStatuse(purchaseSt: PurchaseStatuse) {
        viewModelScope.launch {
            try {
                purchaseStRepository.updatePurchaseSt(purchaseSt)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления статуса")
                showError(t)

            }

        }
    }

    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(1750)
    }
}