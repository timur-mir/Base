package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllEntitys.Prices
import com.example.roomdao30.AllEntitys.PurchasesPrices
import com.example.roomdao30.R

class PurchasesPricesAdapter :
    RecyclerView.Adapter<PurchasesPricesAdapter.PurchasesPricesHolder>() {

    private var purchasesPrices: List<PurchasesPrices> = emptyList()


    class PurchasesPricesHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val idPurchPrice: TextView = view.findViewById(R.id.id_purch_pricePP)
        private val idPrice: TextView = view.findViewById(R.id.id_pricePP)
        private val purchId: TextView = view.findViewById(R.id.id_purchasesPP)
        private val count: TextView = view.findViewById(R.id.countPP)
        fun bind(purchPrices: PurchasesPrices) {
            idPurchPrice.text = purchPrices.id.toString()
            idPrice.text = purchPrices.price_id.toString()
            purchId.text = purchPrices.purchase_id.toString()
            count.text = purchPrices.count.toString()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PurchasesPricesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_purchases_prices, parent, false)
        return PurchasesPricesHolder(view)
    }

    override fun onBindViewHolder(
        holder: PurchasesPricesAdapter.PurchasesPricesHolder,
        position: Int
    ) {
        val purchPrice = purchasesPrices[position]
        holder.bind(purchPrice)
    }

    override fun getItemCount(): Int {
        return purchasesPrices.size
    }

    fun updatePurchPriceAdapter(newList: List<PurchasesPrices>) {
        purchasesPrices = newList
    }
}