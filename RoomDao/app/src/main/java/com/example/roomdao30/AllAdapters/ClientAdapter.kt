package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.R

class ClientAdapter(
    private val OnItemClick: (idClientRez: Int) -> Unit,
    private val OnLongClick: (position: Int, idClientRezDel:Int) -> Unit
) : RecyclerView.Adapter<ClientAdapter.ClientHolder>() {
    private var clients: List<Client> = emptyList()

    class ClientHolder(
        view: View,
        OnItemClick: (idClientRez: Int) -> Unit,
        OnLongClick: (position: Int, idClientRezDel: Int) -> Unit,
        private var currentIdClient: Int? = null
    ) : RecyclerView.ViewHolder(view) {
        private val id: TextView = view.findViewById(R.id.id_client)
        private val name: TextView = view.findViewById(R.id.name_client)
        private val last_name: TextView = view.findViewById(R.id.lastname_client)
        private val email: TextView = view.findViewById(R.id.email_client)
        private val login: TextView = view.findViewById(R.id.login_client)
        private val password: TextView = view.findViewById(R.id.password_client)
        private val avatar: ImageView = view.findViewById(R.id.avatar_client)

        init {
            view.setOnClickListener {
                OnItemClick(currentIdClient!!)
            }

            view.setOnLongClickListener {
                OnLongClick(adapterPosition, currentIdClient!!)
                true
            }
        }

        protected fun bindMainInfo(
            idclient: Int,
            firstName: String,
            lastName: String,
            emailClient: String,
            loginClient: String,
            passwordClient: String,
            avatarClient: String?
        ) {
            id.text = idclient.toString()
            name.text = firstName.toString()
            last_name.text = lastName
            email.text = emailClient
            login.text = loginClient
            password.text = passwordClient
            currentIdClient = idclient

            Glide.with(itemView)
                .load(avatarClient)
                .placeholder(R.drawable.client2)
                .into(avatar)

        }

        fun bind(client: Client) {
            bindMainInfo(
                client.id,
                client.first_name,
                client.last_name,
                client.email,
                client.login,
                client.password,
                client.avatar.toString()
            )
        }


    }

    //////////////////////////////////////**********/////////////////////////////////////
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_client, parent, false)
        return ClientHolder(view, OnItemClick, OnLongClick)
    }

    override fun onBindViewHolder(holder: ClientHolder, position: Int) {
        val client = clients[position]
        holder.bind(client)
    }

    override fun getItemCount(): Int {
        return clients.size
    }

    fun updateClientAdapter(newCourses: List<Client>) {
        clients = newCourses
        notifyDataSetChanged()
    }


}