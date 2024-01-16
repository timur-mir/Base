package com.example.roomdao30.AllFragments


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.roomdao30.AllViewModels.EmployeeViewModel
import com.example.roomdao30.AllViewModels.ProviderViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.all_request_employee_and_product.*
import kotlinx.android.synthetic.main.all_request_employee_and_product.button_ESP
import kotlinx.android.synthetic.main.all_request_provider_and_product.*

class ProvidersSupplyProducts : Fragment(R.layout.all_request_provider_and_product) {
    private val viewModelP by viewModels<ProviderViewModel>()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button_RF.setOnClickListener {
            val li = LayoutInflater.from(requireActivity())
            val provProdView: View = li.inflate(R.layout.id_add_for_request_prov_supply, null)
            val idPrS = provProdView.findViewById<View>(R.id.id_provider_n) as EditText
            AlertDialog.Builder(requireActivity())
                .setView(provProdView)
                .setCancelable(false)
                .setPositiveButton("Запросить", DialogInterface.OnClickListener { _, _ ->
                    if (idPrS.text.isNotBlank()) {
                        viewModelP.loadRequestProvProd(idPrS.text.toString().toInt())

                    } else {
                        Toast.makeText(requireContext(), "Заполните поле", Toast.LENGTH_LONG)
                            .show();
                    }
                    viewModelP.requestProviderProductRelation.observe(viewLifecycleOwner) { provProdRequestList ->
                        val sizeList = provProdRequestList.products.size
                        var s1: String = "";
                        var s2: String = "";
                        var s3: String = "";
                        var s4: String = "";
                        var x: Int = 0
                        id_providerRF.text = provProdRequestList.provider.id_provider.toString()
                        organizationRF.text = provProdRequestList.provider.organization.toString()
                        addressRF.text = provProdRequestList.provider.address.toString()
                        PhoneRF.text=provProdRequestList.provider.phone_number.toString()
                        repeat(sizeList) {
                            s1 += "►"+"│"+"${provProdRequestList.products[x].id}" + "│"+"◄ "
                            s2 += "►"+"│"+"${provProdRequestList.products[x].title.toString()}" + "│"+"◄ "
                            s3 += "►"+"│"+"${provProdRequestList.products[x].avatar.toString()}" + "│"+"◄ "
                            s4 += "►"+"│"+"${provProdRequestList.products[x].description.toString()}" + "│"+"◄ "
                            x++
                        }
                        id_product_RF.text=s1
                        title_RF.text=s2
                        avatar_RF.text=s3
                        description_RF.text=s4
                    }
                }
                )
                .setNegativeButton("Отмена", null)
                .show()
        }
    }
    companion object {
        fun newInstance(): ProvidersSupplyProducts {
            return ProvidersSupplyProducts ()
        }
    }

}