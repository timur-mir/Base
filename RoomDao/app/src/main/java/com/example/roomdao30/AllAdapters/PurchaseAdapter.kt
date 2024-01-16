package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllEntitys.Purchases

import com.example.roomdao30.R

class PurchaseAdapter : RecyclerView.Adapter<PurchaseAdapter.PurchasesHolder>() {
    private var purchases: List<Purchases> = emptyList()

    class PurchasesHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val idPurchase: TextView = view.findViewById(R.id.id_purchase)
        private val idClientP: TextView = view.findViewById(R.id.id_clientP)
        private val crDatePurch: TextView = view.findViewById(R.id.cr_date_purch)
        private val payDatePurch: TextView = view.findViewById(R.id.pay_date_purch)
        private val statusePurch: TextView = view.findViewById(R.id.id_purchaseSt)
        fun bind(purchases: Purchases) {
            idPurchase.text = purchases.id.toString()
            idClientP.text = purchases.client_id.toString()
            crDatePurch.text = purchases.created_at_purch.toString()
            payDatePurch.text = purchases.payed_at.toString()
            statusePurch.text = purchases.statuse_id.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_purchase, parent, false)
        return PurchaseAdapter.PurchasesHolder(view)
    }

    override fun onBindViewHolder(holder: PurchasesHolder, position: Int) {
        val purchase = purchases[position]
        holder.bind(purchase)
    }

    override fun getItemCount(): Int {
        return purchases.size
    }

    fun updatePurchaseAdapter(newList: List<Purchases>) {
        purchases = newList
    }


}