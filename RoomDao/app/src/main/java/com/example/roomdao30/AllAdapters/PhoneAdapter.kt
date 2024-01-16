package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllEntitys.PhoneNumber
import com.example.roomdao30.R

class PhoneAdapter: RecyclerView.Adapter<PhoneAdapter.PhoneHolder>() {
    private var phones: List<PhoneNumber> = emptyList()

    class PhoneHolder(view: View): RecyclerView.ViewHolder(view) {
        private val phoneView: TextView = view.findViewById(R.id.id_phone)
        private val loginView: TextView = view.findViewById(R.id.login_client_phone)
        private val emailView: TextView = view.findViewById(R.id.email_cl_ph)
        fun bind(phoneNumber: PhoneNumber) {
            phoneView.text = phoneNumber.phonenumb
          loginView.text = phoneNumber.logincl
            emailView.text= phoneNumber.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_phone, parent, false)
        return PhoneAdapter.PhoneHolder(view)
    }

    override fun onBindViewHolder(holder: PhoneHolder, position: Int) {
        val phone = phones[position]
        holder.bind(phone)
    }

    override fun getItemCount(): Int {
        return phones.size
    }
    fun updatePhones(newList: List<PhoneNumber>) {
        phones = newList
        notifyDataSetChanged()
    }

}