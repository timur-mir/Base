package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.PhoneNumber
import com.example.roomdao30.AllRepositorys.PhoneNumberRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class PhoneNumberViewModel : ViewModel() {
    private val phoneRepository =
        PhoneNumberRepository()
    private val getPhoneMutableLiveData = MutableLiveData<PhoneNumber?>()
    val updatePhoneLiveData: LiveData<PhoneNumber?>
        get() = getPhoneMutableLiveData

    private val phonesMutableLiveData = MutableLiveData<List<PhoneNumber>>()

    val recordPhonesData: LiveData<List<PhoneNumber>>
        get() = phonesMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent

    fun save(phoneNumberClient: PhoneNumber) {
        viewModelScope.launch {
            try {
                phoneRepository.savePhone(phoneNumberClient)
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения номера")
                showError(t)
            }
        }
    }

    fun loadList() {
        viewModelScope.launch {
            try {
                phonesMutableLiveData.postValue(phoneRepository.getAllPhones())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке номеров ")
            }
        }
    }
//fun removeAllPhone(){
//    viewModelScope.launch {
//        try {
//
//
//        }catch (t: Throwable){
//
//        }
//    }
//}
    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(5551)
    }
}