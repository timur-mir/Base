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
import com.example.roomdao30.AllAdapters.AuthorAdapter
import com.example.roomdao30.AllAdapters.PriceAdapter
import com.example.roomdao30.AllDialogForm.AuthorForm
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Prices
import com.example.roomdao30.AllEntitys.Sale
import com.example.roomdao30.AllViewModels.AuthorViewModel
import com.example.roomdao30.AllViewModels.PriceViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.author_fragment.*
import kotlinx.android.synthetic.main.price_fragment.*
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class PricesFragment:Fragment(R.layout.price_fragment) {
    private val viewModelPrice by viewModels<PriceViewModel>()
    private var priceAdapter: PriceAdapter? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        priceAdapter = PriceAdapter()
        with(price_list) {
            adapter = priceAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        viewModelPrice.loadList()
        viewModelPrice.allPricesData.observe(viewLifecycleOwner) { list ->
            priceAdapter!!.updatePriceAdapter(list)
        }
        add_price.setOnClickListener {
            val li = LayoutInflater.from(requireActivity())
            val saleView: View = li.inflate(R.layout.price_add_form, null)
            val idProdPrice = saleView.findViewById<View>(R.id.product_id_priceF) as EditText
            val pricePrice = saleView.findViewById<View>(R.id.priceLF) as EditText
            val date_price_build = saleView.findViewById<View>(R.id.create_atLF) as TextView
            val butDateCreatePrice = saleView.findViewById<View>(R.id.buttonPD) as Button
            var dateInstant: Instant? = null
            var localDateTime: LocalDateTime
            butDateCreatePrice.setOnClickListener {
              date_price_build.text =
                    LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toString()
                localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
                dateInstant = localDateTime.toInstant(ZoneOffset.UTC)
            }

            AlertDialog.Builder(requireActivity())
                .setView(saleView)
                .setCancelable(false)
                .setTitle("Введите данные прайса")
                .setPositiveButton("Добавить") { _, _ ->
                    if (idProdPrice.text.isNotBlank() && pricePrice.text.isNotBlank() && date_price_build.text.isNotBlank()

                    ) {

                        viewModelPrice.save(
                           Prices(
                            id=0,
                               product_id = idProdPrice.text.toString().toInt(),
                               price=BigDecimal.valueOf(pricePrice.text.toString().toDouble()),
                               create_at = dateInstant ?: Instant.now()
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

      //  priceAdapter!!.updatePriceAdapter(viewModelPrice.recordPricesData.value.orEmpty())

        show_price_list.setOnClickListener {
          updateList()
        }
    }

    fun updateList(){
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, PricesFragment.newInstance())
        transaction.commit()
    }

    companion object {
        fun newInstance(): PricesFragment {
            return PricesFragment()
        }
    }


}