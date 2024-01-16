package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Employee
import com.example.roomdao30.R

class EmployeeAdapter(
    private val OnItemClick: (idEmployeeRez: Int) -> Unit,
    private val OnLongClick: (position: Int, idEmployeeRezDel: Int) -> Unit
) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder>() {
    private var employees: List<Employee> = emptyList()

    class EmployeeHolder(
        view: View,
        OnItemClick: (idEmployeeRez: Int) -> Unit,
        OnLongClick: (position: Int, idEmployeeRezDel: Int) -> Unit,
        private var currentIdEmployee: Int? = null
    ) : RecyclerView.ViewHolder(view) {
        private val idEmpl: TextView = view.findViewById(R.id.id_employee)
        private val emailEmpl: TextView = view.findViewById(R.id.email_employee)
        private val nameEmpl: TextView = view.findViewById(R.id.firstname_employee)
        private val last_nameEmpl: TextView = view.findViewById(R.id.lastname_employee)
        private val postEmpl: TextView = view.findViewById(R.id.post_employee)
        private val avatarEmpl: ImageView = view.findViewById(R.id.avatar_employee)


        init {
            view.setOnClickListener {
                OnItemClick(currentIdEmployee!!)
            }

            view.setOnLongClickListener {
                OnLongClick(adapterPosition, currentIdEmployee!!)
                true
            }
        }

        protected fun bindMainInfo(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            post: String,
            avatar: String?
        ) {
            idEmpl.text = id.toString()
            emailEmpl.text = email.toString()
            nameEmpl.text = firstName.toString()
            last_nameEmpl.text = lastName.toString()
            postEmpl.text = post.toString()

            currentIdEmployee = id.toString().toInt()

            Glide.with(itemView)
                .load(avatar)
                .placeholder(R.drawable.client)
                .into(avatarEmpl)

        }

        fun bind(employee: Employee) {
            bindMainInfo(
                employee.id,
                employee.email,
                employee.first_name,
                employee.last_name,
                employee.post,
                employee.avatar
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_employee, parent, false)
        return EmployeeHolder(view, OnItemClick, OnLongClick)
    }

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        val employee = employees[position]
        holder.bind(employee)
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    fun updateEmployeeAdapter(newList: List<Employee>) {
        employees = newList
        notifyDataSetChanged()
    }
}