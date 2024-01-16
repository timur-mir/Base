package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Rating
import com.example.roomdao30.R

class AuthorAdapter(): RecyclerView.Adapter<AuthorAdapter.AuthorHolder>() {
    private var authors: List<Author> = emptyList()

    class AuthorHolder(view: View):RecyclerView.ViewHolder(view){
        private val idAuthor: TextView = view.findViewById(R.id.id_author)
        private val nameAuthor: TextView = view.findViewById(R.id.authorName)
        fun bind(author: Author) {
            idAuthor.text=author.id.toString()
            nameAuthor.text=author.nameAuthor.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_author, parent, false)
        return AuthorAdapter.AuthorHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorHolder, position: Int) {
        val author = authors[position]
        holder.bind(author)
    }

    override fun getItemCount(): Int {
      return authors.size
    }
    fun updateAuthorAdapter(newList: List<Author>) {
        authors = newList
        notifyDataSetChanged()
    }


}