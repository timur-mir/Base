package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.R

class AllRequestAuthorAdapter(var oNSuccessListener: (allInfoAuthor: String) -> Unit) :
    RecyclerView.Adapter<AllRequestAuthorAdapter.RequestAuthorHolder>() {
    var requestAList: List<ClientWithAuthor> = emptyList()
    companion object {
        var allreport: String? = null
    }

    class RequestAuthorHolder(view: View, var oNSuccessListener: (allInfo: String) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val id_ClientA: TextView = view.findViewById(R.id.id_client_a)
        private val name_ClientA: TextView = view.findViewById(R.id.name_client_a)
        private val last_name_ClientA: TextView = view.findViewById(R.id.lastname_client_a)
        private val email_ClientA: TextView = view.findViewById(R.id.email_client_a)
        private val login_ClientA: TextView = view.findViewById(R.id.login_client_a)
        private val password_ClientA: TextView = view.findViewById(R.id.password_client_a)
        private val avatar_ClientA: TextView = view.findViewById(R.id.avatar_client_a)
        private val id_AuthorA: TextView = view.findViewById(R.id.id_author_a)
        private val name_A: TextView = view.findViewById(R.id.author_name_a)
        protected fun bindMainInfo(
            idClientA: String,
            firstNameA: String,
            lastNameA: String,
            emailClientA: String,
            loginClientA: String,
            passwordClientA: String,
            avatarClientA: String,
            idAuthorA: String,
            nameA: String,

            ) {
            id_ClientA.text = idClientA
            name_ClientA.text = firstNameA
            last_name_ClientA.text = lastNameA
            email_ClientA.text = emailClientA
            login_ClientA.text = loginClientA
            password_ClientA.text = passwordClientA
            avatar_ClientA.text = avatarClientA
            id_AuthorA.text = idAuthorA
            name_A.text = nameA

        }

        fun bind(authorRequstList: ClientWithAuthor) {
            bindMainInfo(
                "│" + authorRequstList.client.id + "│",
                "│" + authorRequstList.client.first_name + "│",
                "│" + authorRequstList.client.last_name + "│",
                "│" + authorRequstList.client.email + "│",
                "│" + authorRequstList.client.login + "│",
                "│" + authorRequstList.client.password + "│",
                "│" + authorRequstList.client.avatar + "│",
                "│" + authorRequstList.author.id + "│",
                "│" + authorRequstList.author.nameAuthor + "│"


            )
            AllRequestAuthorAdapter.allreport = buildString {
                append(
                    "►" + "│" + authorRequstList.client.id + "│",
                    "│" + authorRequstList.client.first_name + "│",
                    "│" + authorRequstList.client.last_name + "│",
                    "│" + authorRequstList.client.email + "│",
                    "│" + authorRequstList.client.login + "│",
                    "│" + authorRequstList.client.password + "│",
                    "│" + authorRequstList.client.avatar + "│",
                    "│" + authorRequstList.author.id + "│",
                    "│" + authorRequstList.author.nameAuthor + "│" + "◄ "
                )
            }
            oNSuccessListener(
                allreport.toString()
            )
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RequestAuthorHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.request_author_form, parent, false)
        return RequestAuthorHolder(view, oNSuccessListener)
    }

    override fun onBindViewHolder(
        holder: AllRequestAuthorAdapter.RequestAuthorHolder,
        position: Int
    ) {
        val requestA = requestAList[position]
        holder.bind(requestA)
    }

    override fun getItemCount(): Int {
     return requestAList.size
    }
    fun updateRequestAuthorAdapter(newResponse: List<ClientWithAuthor>) {
        requestAList = newResponse
        notifyDataSetChanged()
    }

}
