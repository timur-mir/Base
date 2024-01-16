package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Product
import com.example.roomdao30.R

class ProductAdapter(): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    private var products : List<Product> = emptyList()

    class ProductHolder(view: View):RecyclerView.ViewHolder(view) {
        private val idProduct: TextView = view.findViewById(R.id.id_product)
        private val titleProduct: TextView = view.findViewById(R.id.title_P)
        private val avatarP: TextView = view.findViewById(R.id.avatar_P)
        private val descriptionP: TextView = view.findViewById(R.id.description_P)
        fun bind(product: Product) {
            idProduct.text=product.id.toString()
           titleProduct.text=product.title.toString()
           avatarP.text=product.avatar.toString()
           descriptionP.text=product.description.toString()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_product, parent, false)
        return ProductAdapter.ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }
    fun updateProductAdapter(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }


}