package com.example.roomdao30.AllFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.PurchasesPricesAdapter
import com.example.roomdao30.AllEntitys.PurchasesPrices
import com.example.roomdao30.AllViewModels.PurchasesPricesViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.price_fragment.*
import kotlinx.android.synthetic.main.purchases_prices_fragment.*

class PurchasesPricesFragment : Fragment(R.layout.purchases_prices_fragment) {
    private val viewModelPurchPrice by viewModels<PurchasesPricesViewModel>()
    private var purchPricesAdapter: PurchasesPricesAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        purchPricesAdapter = PurchasesPricesAdapter()
        with(purchase_price_list) {
            adapter = purchPricesAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        viewModelPurchPrice.loadList()
        viewModelPurchPrice.recordPurchasesPricesData.observe(viewLifecycleOwner) { purchPriceAllList ->
            purchPricesAdapter!!.updatePurchPriceAdapter(purchPriceAllList)
        }
        add_purch_price.setOnClickListener {
            val li = LayoutInflater.from(requireActivity())
            val purchPriceView: View = li.inflate(R.layout.add_purchases_prices_form, null)
            val idPurchPrice = purchPriceView.findViewById<View>(R.id.id_pricePPF) as EditText
            val idPrice = purchPriceView.findViewById<View>(R.id.id_purchasesPPF) as EditText
            val count = purchPriceView.findViewById<View>(R.id.countPPF) as EditText

            AlertDialog.Builder(requireActivity())
                .setView(purchPriceView)
                .setCancelable(false)
                .setTitle("Введите данные покупки/прайса")
                .setPositiveButton("Добавить") { _, _ ->
                    if (idPurchPrice.text.isNotBlank() && idPrice.text.isNotBlank() && count.text.isNotBlank()) {
                        viewModelPurchPrice.save(
                           PurchasesPrices(
                                id = 0,
                                price_id = idPurchPrice.text.toString().toInt(),
                                purchase_id=idPrice.text.toString().toInt(),
                                count = count.text.toString().toInt())
                        )

                    }
                    else {
                        Toast.makeText(requireContext(), "Заполните поля", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                .setNegativeButton("Отмена", null)
                .show()
                }
        show_purch_price_list.setOnClickListener {
            updateList()
        }
        }
    fun updateList(){
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, PurchasesPricesFragment.newInstance())
        transaction.commit()
    }


    companion object {
        fun newInstance(): PurchasesPricesFragment {
            return PurchasesPricesFragment()
        }
    }
}