package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.AllRelation.EmployeeWithCar
import com.example.roomdao30.R

class AllRequestCarAdapter(var oNSuccessListener: (allInfoCar: String) -> Unit) :
    RecyclerView.Adapter<AllRequestCarAdapter.RequestCarHolder>() {
    var requestCList: List<EmployeeWithCar> = emptyList()

    companion object {
        var allreportC: String? = null
    }


    class RequestCarHolder(view: View, var oNSuccessListener: (allInfoCar: String) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val id_EmplA: TextView = view.findViewById(R.id.id_employee_C)
        private val email_EmplC: TextView = view.findViewById(R.id.email_employee_C)
        private val firs_name_EmplC: TextView = view.findViewById(R.id.firstname_employee_C)
        private val last_name_EmplC: TextView = view.findViewById(R.id.lastname_employee_C)
        private val post_EmplC: TextView = view.findViewById(R.id.post_employee_C)
        private val avatar_EmplC: TextView = view.findViewById(R.id.avatar_C)
        private val id_CarC: TextView = view.findViewById(R.id.id_car_C)
        private val brand_EmplC: TextView = view.findViewById(R.id.brand_c)

        protected fun bindMainInfo(
            idEmpl: String,
            emailEmpl: String,
            firstNameEmpl: String,
            lastNameEmpl: String,
            postEmpl: String,
            avatarEmpl: String,
            idCar: String,
            nameCar: String,

            ) {
            id_EmplA.text = idEmpl
            email_EmplC.text = emailEmpl
            firs_name_EmplC.text = firstNameEmpl
            last_name_EmplC.text = lastNameEmpl
            post_EmplC.text = postEmpl
            avatar_EmplC.text = avatarEmpl
            id_CarC.text = idCar
            brand_EmplC.text = nameCar
        }

        fun bind(carRequstList: EmployeeWithCar) {
            bindMainInfo(
                "│" + carRequstList.employee.id + "│",
                "│" + carRequstList.employee.email + "│",
                "│" + carRequstList.employee.first_name + "│",
                "│" + carRequstList.employee.last_name + "│",
                "│" + carRequstList.employee.post + "│",
                "│" + carRequstList.employee.avatar + "│",
                "│" + carRequstList.car.id + "│",
                "│" + carRequstList.car.car_brand + "│"


            )
            AllRequestCarAdapter.allreportC = buildString {
                append(
                    "►" + "│" + carRequstList.employee.id + "│",
                    "│" + carRequstList.employee.email + "│",
                    "│" + carRequstList.employee.first_name + "│",
                    "│" + carRequstList.employee.last_name + "│",
                    "│" + carRequstList.employee.avatar + "│",
                    "│" + carRequstList.employee.post + "│",
                    "│" + carRequstList.car.id + "│",
                    "│" + carRequstList.car.car_brand + "│" + "◄ "

                )
            }
            oNSuccessListener(
              allreportC.toString()
            )
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestCarHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.request_car_form, parent, false)
        return AllRequestCarAdapter.RequestCarHolder(view, oNSuccessListener)
    }

    override fun onBindViewHolder(holder: RequestCarHolder, position: Int) {
        val requestC = requestCList[position]
        holder.bind(requestC)
    }

    override fun getItemCount(): Int {
       return requestCList.size
    }
    fun updateRequestCarAdapter(newResponse: List<EmployeeWithCar>) {
        requestCList = newResponse
        notifyDataSetChanged()
    }

}