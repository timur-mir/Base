package com.example.roomdao30.AllRepositorys

import android.util.Patterns
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.DB.Database

class ClientRepository {
    private val clientDao = Database.INSTANCE!!.clientDao()

    suspend fun saveClient(client: Client) {
        // if(isClientValid(client).not()) throw IncorrectFormException()
        clientDao.insertClient(client)


    }

    suspend
    fun updateClient(client: Client) {
        // if(isClientValid(client).not()) throw IncorrectFormException()
        clientDao.updateClient(client)

    }

    suspend fun removeClient(client: Client) {
        clientDao.removeClient(client)
    }
    suspend fun removeClientById(clientId: Int) {
        clientDao.removeClientById(clientId)
    }

    suspend fun getClientById(clientId: Int): Client? {
        val result =clientDao.getClientById(clientId )
        return result
    }

    suspend fun getAllClients(): List<Client> {
        return clientDao.getAllClients()
    }
    suspend fun removeAllClients(){
        clientDao.removeAllClients()
    }

    private fun isClientValid(client: Client): Boolean {
        return client.first_name.isNotBlank() &&
                client.last_name.isNotBlank() &&
                client.login.isNotBlank() &&
                client.password.isNotBlank() &&
                Patterns.EMAIL_ADDRESS.matcher(client.email).matches()
    }
}