package com.example.roomdao30.AllFragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.ClientAdapter
import com.example.roomdao30.AllDialogForm.ClientForm
import com.example.roomdao30.AllDialogForm.UpdateClientForm
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllViewModels.AddClientViewModel
import com.example.roomdao30.AllViewModels.ClientListViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.client_fragment.*


class ClientFragment : Fragment(com.example.roomdao30.R.layout.client_fragment) {

    private val viewModel by viewModels<AddClientViewModel>()
    private val clientViewModel by viewModels<ClientListViewModel>()
    private var clientAdapter: ClientAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        clientAdapter =
            ClientAdapter({ idUpdate -> updateClient(idUpdate) }, { it, id -> delete(id, it) })
        with(clients_list) {
            adapter = clientAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
                //itemAnimator = ScaleInAnimator()
            }
        }
        clientViewModel.loadList()
        clientViewModel.recordClientData.observe(viewLifecycleOwner) { clientList ->
            clientAdapter!!.updateClientAdapter(clientList)

        }

        add_client.setOnClickListener {
            val dialogFragmentForAddClient = ClientForm()
            dialogFragmentForAddClient.oNSuccessListener = { newClient ->
                addClient(newClient)
                clients_list.scrollToPosition(0)
            }
            dialogFragmentForAddClient.show(childFragmentManager, "clientDialog")

        }
        clientAdapter!!.updateClientAdapter(clientViewModel.recordClientData.value.orEmpty())


        show_client.setOnClickListener {
            updateList()
        }
        toolbar.title = "Клиенты"
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.updateClient -> {
                    updateList()
                    true
                }

                R.id.deleteAllClient -> {
                    removeAllClients()
                    true
                }

                else -> false

            }
        }
    }

    fun removeAllClients() {
        clientViewModel.removeAllClients()
    }

    fun updateClient(clientIdForUpdate: Int) {
        viewModel.getClientById(clientIdForUpdate)
        viewModel.updateClientLiveData.observe(viewLifecycleOwner) { clientList ->
            getList(clientList)
            viewModel.updateClientLiveData.removeObservers(viewLifecycleOwner)
        }
    }

    private fun getList(clientListUp: Client?) {
        val dialogFragmentForUpdateClient = UpdateClientForm(clientListUp)
        dialogFragmentForUpdateClient.oNSuccessListener = { updateClientList ->
            viewModel.updateClient(updateClientList)
        }
        dialogFragmentForUpdateClient.show(childFragmentManager, "update")


    }

    fun addClient(list: Client) {
        viewModel.save(list)
        Toast.makeText(
            requireContext(),
            "Добавление данных клиента:${list.first_name}",
            Toast.LENGTH_LONG
        ).show();


    }

    fun removeClient(client: Client) {
        clientViewModel.removeClient(client)
    }

    fun delete(clientId: Int, pos: Int) {
        clientViewModel.removeClientById(clientId)
        clientViewModel.loadList()
        clientAdapter!!.updateClientAdapter(clientViewModel.recordClientData.value.orEmpty())

        Toast.makeText(
            requireContext(),
            "Удаление записи клиента с Идентификационный номер:$clientId",
            Toast.LENGTH_LONG
        ).show();
    }

    fun updateList() {
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, ClientFragment.newInstance())
        transaction.commit()
    }

    companion object {
        fun newInstance(): ClientFragment {
            return ClientFragment()
        }
    }
}