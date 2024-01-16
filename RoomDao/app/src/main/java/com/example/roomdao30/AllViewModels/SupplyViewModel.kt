package com.example.roomdao30.AllViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.Supply
import com.example.roomdao30.AllRepositorys.SupplyRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class SupplyViewModel:ViewModel() {
    private val supplyRepository = SupplyRepository()
    private val getSupplyMutableLiveData = MutableLiveData<Supply?>()

    val updateSupplyLiveData: LiveData<Supply?>
        get() = getSupplyMutableLiveData

//    private val requestCarForEmployee = MutableLiveData<List<EmployeeWithCar>>()
//    //
//    val requestCarList: LiveData<List<EmployeeWithCar>>
//        get() = requestCarForEmployee

    private val supplyMutableLiveData = MutableLiveData<List<Supply>>()

    val recordSupplyData: LiveData<List<Supply>>
        get() = supplyMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent


//    fun requestCars() {
//        viewModelScope.launch {
//            try {
//                requestCarForEmployee.postValue(carRepository.requestCar())
//            } catch (t: Throwable) {
//                Timber.e(t, "Ошибка в запросе автомобиля $t ")
//            }
//        }
//    }

    fun loadList() {
        viewModelScope.launch {
            try {
                supplyMutableLiveData.postValue(supplyRepository.getAllSupplys())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке поставок ")
            }
        }
    }

    fun removeSupplyById(supId: Int) {
        viewModelScope.launch {
            try {
                supplyRepository.removeSupplyById(supId)
            } catch (t: Throwable) {
                Timber.e(t)
            }

        }
    }

    fun getSupplyById(supId: Int) {
        viewModelScope.launch {
            try {
                val supply= supplyRepository.getSupplyById(supId)
                getSupplyMutableLiveData.postValue(supply)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun save(supply: Supply) {
        viewModelScope.launch {
            try {
                if (supply.id== 0) {
                    supplyRepository.saveSupply(supply)
                } else {
                    supplyRepository.updateSupply(supply)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения поставщика")
                showError(t)
            }
        }
    }

    fun updateProvider(supply: Supply) {
        viewModelScope.launch {
            try {
               supplyRepository.updateSupply(supply)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления поставки")
                showError(t)

            }

        }
    }
    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(2950)
    }

}