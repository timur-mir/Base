package com.example.roomdao30.AllFragments


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.roomdao30.AllAdapters.SaleAdapter

import com.example.roomdao30.AllEntitys.Sale

import com.example.roomdao30.AllViewModels.SaleViewModel
import com.example.roomdao30.R

import kotlinx.android.synthetic.main.sale_fragment.*
import java.time.Instant
import java.math.BigDecimal

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class SaleFragment : Fragment(R.layout.sale_fragment) {
    private val viewModel by viewModels<SaleViewModel>()
    private var saleAdapter: SaleAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        saleAdapter = SaleAdapter()
        with(sale_list) {
            adapter = saleAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        viewModel.loadListSale()
        viewModel.recordSaleData.observe(viewLifecycleOwner) { saleList ->
            saleAdapter!!.updateSaleAdapter(saleList)
        }

        add_sale.setOnClickListener {
            val li = LayoutInflater.from(requireActivity())
            val saleView: View = li.inflate(R.layout.sale_add_form, null)
            val idProdSale = saleView.findViewById<View>(R.id.id_prod_SL) as EditText
            val payDate = saleView.findViewById<View>(R.id.pay_dateSL) as TextView
            val quantsl = saleView.findViewById<View>(R.id.quantitySL) as EditText
            val price = saleView.findViewById<View>(R.id.priceSL) as EditText
            val idEmployeeSl = saleView.findViewById<View>(R.id.id_employeeSL) as EditText
            val butDate = saleView.findViewById<View>(R.id.buttonSL) as Button
            var dateInstant: Instant? = null
            var localDateTime: LocalDateTime
            butDate.setOnClickListener {
                payDate.text =
                    LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toString()
                localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
                dateInstant = localDateTime.toInstant(ZoneOffset.UTC)
            }
            AlertDialog.Builder(requireActivity())
                .setView(saleView)
                .setCancelable(false)
                .setPositiveButton("Добавить") { _, _ ->
                    if (idProdSale.text.isNotBlank() && quantsl.text.isNotBlank() && price.text.isNotBlank()
                        && idEmployeeSl.text.isNotBlank()
                    ) {

                        viewModel.save(

                            Sale(
                                id = 0,
                                id_products = idProdSale.text.toString().toInt(),
                                pay_date = dateInstant ?: Instant.now(),
                                quantity = quantsl.text.toString().toInt(),
                                total_price = BigDecimal.valueOf(price.text.toString().toDouble()),
                                id_employee = idEmployeeSl.text.toString().toInt()
                            )
                        )
                    } else {
                        Toast.makeText(requireContext(), "Заполните поля", Toast.LENGTH_LONG)
                            .show()

                    }
                }

                .setNegativeButton("Отмена", null)
                .show()

        }
        show_sale.setOnClickListener{
            updateList()
        }


    }
    fun updateList(){
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, SaleFragment.newInstance())
        transaction.commit()
    }


    companion object {
        fun newInstance(): SaleFragment {
            return SaleFragment()
        }
    }
}