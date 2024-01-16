package com.example.roomdao30.AllFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.PurchaseAdapter
import com.example.roomdao30.AllDialogForm.PurchaseForm
import com.example.roomdao30.AllViewModels.PurchasesViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.purchase_fragment.*

class Purchases : Fragment(R.layout.purchase_fragment) {
    private val vMPurchase by viewModels<PurchasesViewModel>()
    private var purchaseAdapter: PurchaseAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        purchaseAdapter = PurchaseAdapter()
        with(purchase_list) {
            adapter = purchaseAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
//
        }

        vMPurchase.loadList()
        vMPurchase.recordPurchasesData.observe(viewLifecycleOwner, Observer { listP-> purchaseAdapter!!.updatePurchaseAdapter(listP) })

   add_purchase.setOnClickListener {
       val dfragPurchase=PurchaseForm()
       dfragPurchase.oNSuccessListener={purchEnt->vMPurchase.save(purchEnt)
       purchase_list.scrollToPosition(0)}
       dfragPurchase.show(childFragmentManager,"purchase dialog")
   }
        purchaseAdapter !!.updatePurchaseAdapter(vMPurchase.recordPurchasesData.value.orEmpty())
        show_purchase.setOnClickListener {
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, Purchases.newInstance())
            transaction.commit()
        }

    }


    companion object {
        fun newInstance(): Purchases {
            return Purchases()
        }
    }
}