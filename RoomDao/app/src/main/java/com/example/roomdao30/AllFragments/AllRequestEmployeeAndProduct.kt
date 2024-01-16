package com.example.roomdao30.AllFragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.roomdao30.AllEntitys.BookShop
import com.example.roomdao30.AllEntitys.Employee
import com.example.roomdao30.AllViewModels.AuthorViewModel
import com.example.roomdao30.AllViewModels.EmployeeViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.all_request_employee_and_product.*

class AllRequestEmployeeAndProduct: Fragment(R.layout.all_request_employee_and_product){
    private val viewModelE by viewModels<EmployeeViewModel>()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            button_ESP.setOnClickListener{
                val li = LayoutInflater.from(requireActivity())
                val emplProdView: View = li.inflate(R.layout.id_add_for_request_e_p, null)
                val idEPS = emplProdView.findViewById<View>(R.id.id_employeeESP) as EditText

                AlertDialog.Builder(requireActivity())
                    .setView(emplProdView)
                    .setCancelable(false)
                    .setPositiveButton("Запросить", DialogInterface.OnClickListener { _, _ ->
                        if (idEPS.text.isNotBlank() ) {
                                viewModelE.loadRequestEplProd(idEPS.text.toString().toInt())

                        } else {
                            Toast.makeText(requireContext(), "Заполните поле", Toast.LENGTH_LONG)
                                .show();
                        }
                        viewModelE.requestEmployeeProductRelation.observe(viewLifecycleOwner) { emplProdRequestList ->
                            val sizeList = emplProdRequestList.products.size
                            var s1: String = "";
                            var s2: String = "";
                            var s3: String = "";
                            var s4: String = "";
                            var x: Int = 0
                            id_employee_ESP.text = emplProdRequestList.employee.id.toString()
                            email_employee_ESP.text = emplProdRequestList.employee.email
                            firstname_employee_ESP.text = emplProdRequestList.employee.first_name
                            lastname_employee_ESP.text = emplProdRequestList.employee.last_name
                            post_employee_ESP.text = emplProdRequestList.employee.post
                            avatar_employee_ESP.text = emplProdRequestList.employee.avatar
                            repeat(sizeList) {
                                s1 += "►"+"│"+"${emplProdRequestList.products[x].id}" + "│"+"◄ "
                                s2 += "►"+"│"+"${emplProdRequestList.products[x].title.toString()}" + "│"+"◄ "
                                s3 += "►"+"│"+"${emplProdRequestList.products[x].avatar.toString()}" + "│"+"◄ "
                                s4 += "►"+"│"+"${emplProdRequestList.products[x].description.toString()}" + "│"+"◄ "
                                x++
                            }
                            id_product_ESP.text=s1
                            title_ESP.text=s2
                            avatar_ESP.text=s3
                            description_ESP.text=s4
                        }
                    })

                    .setNegativeButton("Отмена", null)
                    .show()
            }
            }


    companion object {
        fun newInstance(): AllRequestEmployeeAndProduct {
            return AllRequestEmployeeAndProduct ()
        }
    }
}