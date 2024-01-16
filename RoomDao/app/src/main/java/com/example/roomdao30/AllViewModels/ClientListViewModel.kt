package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllRepositorys.ClientRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class ClientListViewModel : ViewModel() {

    private val clientRepository = ClientRepository()

    private val clientsMutableLiveData = MutableLiveData<List<Client>>()

    val recordClientData: LiveData<List<Client>>
        get() = clientsMutableLiveData

        fun loadList(){
            viewModelScope.launch {
                try{
                    clientsMutableLiveData.postValue(clientRepository.getAllClients())
                } catch (t:Throwable){
                    Timber.e(t,"Ошибка в списке клиента ")
                }
            }
        }
    fun removeClient(client: Client){
        viewModelScope.launch {
            try{
                clientRepository.removeClient(client)
            }catch (t:Throwable){
                Timber.e(t)
            }

        }
    }
    fun removeClientById(client: Int){
        viewModelScope.launch {
            try{
                clientRepository.removeClientById(client)
            }catch (t:Throwable){
                Timber.e(t)
            }

        }
    }
    fun removeAllClients(){
        viewModelScope.launch {
            try {
                clientRepository.removeAllClients()
            }catch (t: Throwable){
                Timber.e(t)
            }
        }
    }

}