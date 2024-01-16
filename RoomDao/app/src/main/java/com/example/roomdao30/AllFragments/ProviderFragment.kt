package com.example.roomdao30.AllFragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.ShareActionProvider
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.ClientAdapter
import com.example.roomdao30.AllAdapters.ProviderAdapter
import com.example.roomdao30.AllDialogForm.UpdateClientForm
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Provider
import com.example.roomdao30.AllViewModels.ClientListViewModel
import com.example.roomdao30.AllViewModels.ProductViewModel
import com.example.roomdao30.AllViewModels.ProviderViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.client_fragment.*
import kotlinx.android.synthetic.main.list_provider.*
import kotlinx.android.synthetic.main.provider_fragment.*

class ProviderFragment : Fragment(R.layout.provider_fragment) {
    private val providerViewModel by viewModels<ProviderViewModel>()
    private var providerAdapter: ProviderAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        providerAdapter =
            ProviderAdapter({ idUpdate -> updateProvider(idUpdate) }, { _, id -> delete(id) })
        with(provider_list) {
            adapter = providerAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
                //itemAnimator = ScaleInAnimator()
            }
        }
        providerViewModel.loadList()
        providerViewModel.recordProviderData.observe(viewLifecycleOwner) { providerList ->
            providerAdapter!!.updateProviderAdapter(providerList)

        }
        add_provider.setOnClickListener {
            val li = LayoutInflater.from(requireActivity())
            val providerView: View = li.inflate(R.layout.prov_add_form, null)
            val organizProv = providerView.findViewById<View>(R.id.organisationProvL) as EditText
            val organizAddress = providerView.findViewById<View>(R.id.addressProvL) as EditText
            val organizPhone = providerView.findViewById<View>(R.id.phoneProvL) as EditText

            AlertDialog.Builder(requireActivity())
                .setView(providerView)
                .setCancelable(false)
                .setTitle("Введите данные поставщика")
                .setPositiveButton("Добавить", DialogInterface.OnClickListener { _, _ ->
                    if (organizProv.text.isNotBlank() && organizAddress.text.isNotBlank() && organizPhone.text.isNotBlank()) {
                        providerViewModel.save(
                            Provider(
                                id_provider = 0,
                                organization = organizProv.text.toString(),
                                address = organizAddress.text.toString(),
                                phone_number = organizPhone.text.toString()
                            )
                        )
                    } else {
                        Toast.makeText(requireContext(), "Заполните поля", Toast.LENGTH_LONG)
                            .show()
                    }

                })

                .setNegativeButton("Отмена", null)
                .show()
        }
        show_provider_list.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "${providerViewModel.saveErrorLiveData.value}",
                Toast.LENGTH_LONG
            ).show()
updateList()

            }
        }
    fun updateList(){
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, ProviderFragment.newInstance())
        transaction.commit()
    }



    fun updateProvider(providerIdForUpdate: Int) {
        providerViewModel.getProviderById(providerIdForUpdate)
        providerViewModel.updateProviderLiveData.observe(viewLifecycleOwner) { it ->
            getList(it)

            providerViewModel.updateProviderLiveData.removeObservers(viewLifecycleOwner)
        }

    }

    private fun getList(provList:Provider?) {
        val dialogFragmentForUpdateClient = UpdateProviderForm(provList)
        dialogFragmentForUpdateClient.oNSuccessListener = { updateProviderList ->
            providerViewModel.updateProvider(updateProviderList)}
        dialogFragmentForUpdateClient.show(childFragmentManager, "update")
            }


    fun delete(provId: Int) {
        providerViewModel.removeProviderById(provId)
        providerViewModel.loadList()
        providerAdapter!!.updateProviderAdapter(providerViewModel.recordProviderData.value.orEmpty())
        Toast.makeText(
            requireContext(),
            "Удаление записи поставщика с номер:$provId",
            Toast.LENGTH_LONG
        ).show();
    }

    companion object {
        fun newInstance(): ProviderFragment {
            return ProviderFragment()
        }
    }

}
