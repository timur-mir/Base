package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.PurchaseStatuse
import com.example.roomdao30.R

class PurchaseStatuseAdapter :
    RecyclerView.Adapter<PurchaseStatuseAdapter.PurchasesStatusesHolder>() {

    private var purchStatuses: List<PurchaseStatuse> = emptyList()


    class PurchasesStatusesHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val idStatusePurch: TextView = view.findViewById(R.id.id_purch_statuse)
        private val titlePurchaseStatuse: TextView = view.findViewById(R.id.title_statuse)
        fun bind(statusePurch: PurchaseStatuse) {
            idStatusePurch.text = statusePurch.id.toString()
            titlePurchaseStatuse.text = statusePurch.title.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PurchaseStatuseAdapter.PurchasesStatusesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_purchase_statuse, parent, false)
        return PurchaseStatuseAdapter.PurchasesStatusesHolder(view)
    }

    override fun onBindViewHolder(
        holder: PurchaseStatuseAdapter.PurchasesStatusesHolder,
        position: Int
    ) {
        val purchSt = purchStatuses[position]
        holder.bind(purchSt)
    }

    override fun getItemCount(): Int {
        return purchStatuses.size
    }
    fun updatePurchaseStatuseAdapter(newList: List<PurchaseStatuse>) {
        purchStatuses = newList
        notifyDataSetChanged()
    }

}