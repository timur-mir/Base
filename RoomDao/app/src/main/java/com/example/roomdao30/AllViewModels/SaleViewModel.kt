package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.Rating
import com.example.roomdao30.AllEntitys.Sale
import com.example.roomdao30.AllRepositorys.RatingRepository
import com.example.roomdao30.AllRepositorys.SaleRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class SaleViewModel : ViewModel() {
    private val saleRepository = SaleRepository()

    //Для формы обновления данных
    private val getSaleMutableLiveData = MutableLiveData<Sale?>()
    val updateSaleLiveData: LiveData<Sale?>
        get() = getSaleMutableLiveData
    private val saleMutableLiveData = MutableLiveData<List<Sale>>()
    val recordSaleData: LiveData<List<Sale>>
        get() = saleMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()
    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent
    private val saveErrorLiveEvent = MutableLiveData<Int>()
    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent

    fun loadListSale() {
        viewModelScope.launch {
            try {
                saleMutableLiveData.postValue(saleRepository.getAllSales())
            } catch (t: Throwable) {
                Timber.e(t.message, "Ошибка в списке продаж")
            }
        }
    }

    fun removeSaleById(saleId: Int) {
        viewModelScope.launch {
            try {
                saleRepository.removeSaleById(saleId)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun getSaleEmployeeById(id: Int) {
        viewModelScope.launch {
            try {
                val sale = saleRepository.getSaleById(id)
              //  getSaleMutableLiveData.postValue(sale)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun save(sale: Sale) {
        viewModelScope.launch {
            try {
                if (sale.id == 0) {
                    saleRepository.saveSale(sale)
                } else {
                    saleRepository.updateSale(sale)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения продажи")
                showError(t)
            }
        }
    }

    fun updateSale(sale: Sale) {
        viewModelScope.launch {
            try {
                saleRepository.updateSale(sale)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления продажи")
                showError(t)

            }

        }
    }

    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(3000)
    }

}