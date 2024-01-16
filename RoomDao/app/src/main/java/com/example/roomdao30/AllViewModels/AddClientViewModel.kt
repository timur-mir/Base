package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllRepositorys.ClientRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class AddClientViewModel : ViewModel() {

    private val clientRepository = ClientRepository()

    private val existingClientsMutableLiveData = MutableLiveData<Client>()

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    private val getClientErrorMutableLiveData =MutableLiveData<Client?>()

    private  val  getClientMutableLiveData=MutableLiveData<Client?>()


    val existingClientLiveData: LiveData<Client>
        get() = existingClientsMutableLiveData

    val updateClientLiveData: LiveData<Client?>
    get() = getClientMutableLiveData


    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent

    fun init(id: Int) {
        viewModelScope.launch {
            try {
                val client = clientRepository.getClientById(id)
                if (client != null) existingClientsMutableLiveData.postValue(client!!)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }

    }
    fun getClientById(id:Int){
        viewModelScope.launch {
            try {
               val client= clientRepository.getClientById(id)
                getClientMutableLiveData.postValue(client)
            } catch (t:Throwable){
                Timber.e(t)
            }
        }
    }


    fun save(client:Client) {
        viewModelScope.launch {
            try {
                if (client.id == 0) {
                    clientRepository.saveClient(client)
                } else {
                    clientRepository.updateClient(client)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения клиента")
                showError(t)
            }
        }
    }
        fun updateClient(client: Client) {
            viewModelScope.launch {
                try {
                    clientRepository.updateClient(client)
                } catch (t: Throwable) {
                    Timber.e(t, "Ошибка сохранения клиента")
                    showError(t)

                }

            }
        }


                private fun showError(t: Throwable) {
                    saveErrorLiveEvent.postValue(1000)
                }
//                    when(t){
//                        is  IncorrectFormException->"Некорректные данные"
//                        else ->"Ошибка сохранения клиента"
//                    }

            }
