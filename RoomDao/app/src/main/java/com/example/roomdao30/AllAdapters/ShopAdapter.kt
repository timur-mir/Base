package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllEntitys.BookShop
import com.example.roomdao30.AllEntitys.Product
import com.example.roomdao30.R

class ShopAdapter : RecyclerView.Adapter<ShopAdapter.ShopHolder>() {
    private var shops: List<BookShop> = emptyList()

    class ShopHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val idShop: TextView = view.findViewById(R.id.id_shop)
        private val titleShop: TextView = view.findViewById(R.id.title_S)
        private val addressShop: TextView = view.findViewById(R.id.address_S)
        private val phoneShop: TextView = view.findViewById(R.id.phone_S)

        fun bind(shop: BookShop) {
            idShop.text = shop.id.toString()
            titleShop.text = shop.title.toString()
            addressShop.text = shop.address.toString()
            phoneShop.text = shop.phone_number.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopAdapter.ShopHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_shop, parent, false)
        return ShopAdapter.ShopHolder(view)

    }

    override fun onBindViewHolder(holder: ShopAdapter.ShopHolder, position: Int) {
        val shop = shops[position]
        holder.bind(shop)
    }

    override fun getItemCount(): Int {
        return shops.size
    }
    fun updateShopAdapter(newList: List<BookShop>) {
        shops = newList
        notifyDataSetChanged()
    }

}