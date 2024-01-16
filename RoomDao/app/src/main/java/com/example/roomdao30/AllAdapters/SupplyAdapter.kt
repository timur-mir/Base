package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Supply
import com.example.roomdao30.R

class SupplyAdapter: RecyclerView.Adapter<SupplyAdapter.SupplyHolder>() {

    private var supplys: List<Supply> = emptyList()

    class SupplyHolder(view: View):RecyclerView.ViewHolder(view) {
        private val idSupply: TextView = view.findViewById(R.id.id_supply)
        private val supplySize: TextView = view.findViewById(R.id.supply_size)
        private val idProvider: TextView = view.findViewById(R.id.id_provider)
        private val idProduct: TextView = view.findViewById(R.id.id_product)

        fun bind(supply:Supply) {
            idSupply.text=supply.id.toString()
            supplySize.text=supply.supply_size.toString()
            idProvider.text=supply.id_provider.toString()
            idProduct.text=supply.id_product.toString()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplyAdapter.SupplyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_supply, parent, false)
        return SupplyAdapter.SupplyHolder(view)
    }

    override fun onBindViewHolder(holder: SupplyAdapter.SupplyHolder, position: Int) {
        val supply = supplys[position]
        holder.bind(supply)
    }

    override fun getItemCount(): Int {
       return supplys.size
    }
    fun updateSupplyAdapter(newList: List<Supply>) {
        supplys = newList
        notifyDataSetChanged()
    }
}