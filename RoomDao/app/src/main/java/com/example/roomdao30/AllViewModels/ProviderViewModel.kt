package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.OfficialCar
import com.example.roomdao30.AllEntitys.Provider
import com.example.roomdao30.AllRelation.EmployeeAndProductsRelation
import com.example.roomdao30.AllRelation.EmployeeWithCar
import com.example.roomdao30.AllRelation.ProvidersAndProductsRelation
import com.example.roomdao30.AllRepositorys.ClientRepository
import com.example.roomdao30.AllRepositorys.ProviderRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class ProviderViewModel:ViewModel() {
    private val providerRepository = ProviderRepository()

    private val getProviderMutableLiveData = MutableLiveData<Provider?>()
    val updateProviderLiveData: LiveData<Provider?>
        get() = getProviderMutableLiveData

//    private val requestCarForEmployee = MutableLiveData<List<EmployeeWithCar>>()
//    //
//    val requestCarList: LiveData<List<EmployeeWithCar>>
//        get() = requestCarForEmployee

    private val providersMutableLiveData = MutableLiveData<List<Provider>>()
    val recordProviderData: LiveData<List<Provider>>
        get() = providersMutableLiveData

    private val getProviderSupplyProduct=MutableLiveData<ProvidersAndProductsRelation>()
    val requestProviderProductRelation:LiveData<ProvidersAndProductsRelation>
        get() = getProviderSupplyProduct

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent

    fun loadRequestProvProd(providerId:Int){
        viewModelScope.launch {
            try {
                getProviderSupplyProduct.postValue(providerRepository.getAllProviderAndProductsWithRelations(providerId))
            }catch (t:Throwable){
                Timber.e(t, "Ошибка в запросе ")
            }
        }
    }

    fun loadList() {
        viewModelScope.launch {
            try {
                providersMutableLiveData.postValue(providerRepository.getAllProviders())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке поставщиков ")
            }
        }
    }

    fun removeProviderById(provId: Int) {
        viewModelScope.launch {
            try {
               providerRepository.removeProviderById(provId)
            } catch (t: Throwable) {
                Timber.e(t)
            }

        }
    }

    fun getProviderById(provId: Int) {
        viewModelScope.launch {
            try {
                val provider= providerRepository.getProviderById(provId)
                getProviderMutableLiveData.postValue(provider)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun save(provider: Provider) {
        viewModelScope.launch {
            try {
                if (provider.id_provider== 0) {
                    providerRepository.saveProvider(provider)
                } else {
                    providerRepository.updateProvider(provider)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения поставщика")
                showError(t)
            }
        }
    }

    fun updateProvider(provider: Provider) {
        viewModelScope.launch {
            try {
                providerRepository.updateProvider(provider)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления поставщика")
                showError(t)

            }

        }
    }

    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(1850)
    }



}