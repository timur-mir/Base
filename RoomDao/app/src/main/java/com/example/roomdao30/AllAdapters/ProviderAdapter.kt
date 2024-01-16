package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Provider
import com.example.roomdao30.R

class ProviderAdapter(
    private val OnItemClick: (idProvRez: Int) -> Unit,
    private val OnLongClick: (position: Int, idProvRezDel: Int) -> Unit
) : RecyclerView.Adapter<ProviderAdapter.ProviderHolder>() {

    private var providers: List<Provider> = emptyList()

    class ProviderHolder(
        view: View,
        OnItemClick: (idProvRez: Int) -> Unit,
        OnLongClick: (position: Int, idProvRez: Int) -> Unit,
        private var currentIdCProvider: Int? = null
    ) : RecyclerView.ViewHolder(view) {
        private val id: TextView = view.findViewById(R.id.id_provider)
        private val organizP: TextView = view.findViewById(R.id.organisationProv)
        private val addressP: TextView = view.findViewById(R.id.addressProv)
        private val phoneP: TextView = view.findViewById(R.id.phoneProv)


        init {
            view.setOnClickListener {
                currentIdCProvider?.let{ OnItemClick(it)}
            }

            view.setOnLongClickListener {
                OnLongClick(adapterPosition, currentIdCProvider!!)
                true
            }
        }

        protected fun bindMainInfo(
            idcProv: Int,
            organizProv: String,
            addressProv: String,
            phoneProv: String,

            ) {
            id.text = idcProv.toString()
            organizP.text = organizProv.toString()
            addressP.text = addressProv.toString()
            phoneP.text = phoneProv.toString()
            currentIdCProvider=idcProv
        }

        fun bind(provider: Provider) {
            bindMainInfo(
                provider.id_provider,
                provider.organization,
                provider.address,
                provider.phone_number
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_provider, parent, false)
        return ProviderAdapter.ProviderHolder(view, OnItemClick, OnLongClick)
    }

    override fun onBindViewHolder(holder: ProviderHolder, position: Int) {
        val provider = providers[position]
        holder.bind(provider)
    }

    override fun getItemCount(): Int {
       return providers.size
    }
    fun updateProviderAdapter(newCourses: List<Provider>) {
       providers = newCourses
        notifyDataSetChanged()
    }


}