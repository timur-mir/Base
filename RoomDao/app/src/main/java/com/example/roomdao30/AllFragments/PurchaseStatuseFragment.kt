package com.example.roomdao30.AllFragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.PriceAdapter
import com.example.roomdao30.AllAdapters.PurchaseStatuseAdapter
import com.example.roomdao30.AllEntitys.PurchaseStatuse
import com.example.roomdao30.AllViewModels.PurchasesStatusViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.purchase_statuse_fragment.*

class PurchaseStatuseFragment : Fragment(R.layout.purchase_statuse_fragment) {
    private val viewModelPurchStatuse by viewModels<PurchasesStatusViewModel>()
    private var purchStatuseAdapter: PurchaseStatuseAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        purchStatuseAdapter = PurchaseStatuseAdapter()
        with(purchases_statuses_list) {
            adapter = purchStatuseAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
                setHasFixedSize(true)
            }
        }
        viewModelPurchStatuse.loadList()
        viewModelPurchStatuse.recordPurchasesStatuseData.observe(viewLifecycleOwner) { listSt ->
            purchStatuseAdapter!!.updatePurchaseStatuseAdapter(listSt)
        }
        add_purchase_statuse.setOnClickListener {
            val li = LayoutInflater.from(requireActivity())
            val statusePView: View = li.inflate(R.layout.purchase_statuse_add_form, null)
            val statuseEdit = statusePView.findViewById<View>(R.id.statuse_input_field) as EditText
            AlertDialog.Builder(requireActivity())
                .setView(statusePView)
                .setCancelable(false)
                .setTitle("Введите статус")
                .setPositiveButton("Добавить") { _, _ ->
                    if (statuseEdit.text.isNotBlank()) {
                        viewModelPurchStatuse.save(
                            PurchaseStatuse(
                                id = 0,
                                title = statuseEdit.text.toString()
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
        show_purchase_statuse.setOnClickListener {
            updateList()
        }

    }

    fun updateList() {
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, PurchaseStatuseFragment.newInstance())
        transaction.commit()
    }


    companion object {
        fun newInstance(): PurchaseStatuseFragment {
            return PurchaseStatuseFragment()
        }
    }
}