package com.example.roomdao30.AllAdapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllEntitys.Product
import com.example.roomdao30.AllEntitys.Rating
import com.example.roomdao30.AllEntitys.Sale
import com.example.roomdao30.R
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class SaleAdapter() : RecyclerView.Adapter<SaleAdapter.SaleHolder>() {

    private var sales: List<Sale> = emptyList()

    class SaleHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val idSale: TextView = view.findViewById(R.id.id_sale_Sl)
        private val productSl: TextView = view.findViewById(R.id.id_product_Sl)
        private val payDate: TextView = view.findViewById(R.id.id_pay_date_Sl)
        private val quantity: TextView = view.findViewById(R.id.quantity_Sl)
        private val totalPrice: TextView = view.findViewById(R.id.total_price_Sl)
        private val employeeId: TextView = view.findViewById(R.id.id_employee_Sl)

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n")
        fun bind(sale: Sale) {
            idSale.text = sale.id.toString()
            productSl.text = sale.id_products.toString()
            payDate.text = sale.pay_date.toString()
            quantity.text = sale.quantity.toString()
            totalPrice.text = sale.total_price.toString()
            employeeId.text = sale.id_employee.toString()

        }
       // LocalDateTime.ofInstant(sale.pay_date, ZoneId.systemDefault()).toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_sale, parent, false)
        return SaleAdapter.SaleHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: SaleHolder, position: Int) {
        val sale = sales[position]
        holder.bind(sale)
    }

    override fun getItemCount(): Int {
      return  sales.size
    }
    fun updateSaleAdapter(newSale: List<Sale>) {
        sales = newSale
        notifyDataSetChanged()
    }

}