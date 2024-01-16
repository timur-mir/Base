package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Prices
import com.example.roomdao30.R

class PriceAdapter: RecyclerView.Adapter<PriceAdapter.PriceHolder>() {
    private var prices: List<Prices> = emptyList()

    class PriceHolder(view: View):RecyclerView.ViewHolder(view){
        private val idPrice: TextView = view.findViewById(R.id.id_price)
        private val productIdPrice: TextView = view.findViewById(R.id.product_id_price)
        private val priceL: TextView = view.findViewById(R.id.priceL)
        private val dateCreateP: TextView = view.findViewById(R.id.create_atL)
        fun bind(prices: Prices) {
            idPrice.text=prices.id.toString()
            productIdPrice.text=prices.product_id.toString()
            priceL.text=prices.price.toString()
           dateCreateP.text=prices.create_at.toString()
        }
        }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceAdapter.PriceHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_price, parent, false)
        return PriceAdapter.PriceHolder(view)
    }

    override fun onBindViewHolder(holder: PriceAdapter.PriceHolder, position: Int) {
        val price = prices[position]
        holder.bind(price)
    }

    override fun getItemCount(): Int {
      return prices.size
    }
    fun updatePriceAdapter(newList: List<Prices>) {
        prices = newList
        notifyDataSetChanged()
    }


}