package com.example.roomdao30.AllFragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.ClientAdapter
import com.example.roomdao30.AllAdapters.EmployeeAdapter
import com.example.roomdao30.AllEntitys.Employee
import com.example.roomdao30.AllEntitys.Product
import com.example.roomdao30.AllViewModels.AddClientViewModel
import com.example.roomdao30.AllViewModels.ClientListViewModel
import com.example.roomdao30.AllViewModels.EmployeeViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.client_fragment.*
import kotlinx.android.synthetic.main.employee_fragment.*
import kotlinx.android.synthetic.main.product_fragment.*

class EmployeeFragment: Fragment(R.layout.employee_fragment) {
    private val viewModel by viewModels <EmployeeViewModel>()
    private var employeeAdapter: EmployeeAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        employeeAdapter =
            EmployeeAdapter({ idUpdate -> updateEmpl(idUpdate) }, { it, id -> delete(id, it) })
        with(employee_list) {
            adapter = employeeAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
                //itemAnimator = ScaleInAnimator()
            }
        }
        viewModel.loadList()
        viewModel.recordEmployeeData.observe(viewLifecycleOwner) { employeeList ->
            employeeAdapter!!.updateEmployeeAdapter(employeeList)

        }
        add_employee.setOnClickListener{
            val li = LayoutInflater.from(requireActivity())
            val emplView: View = li.inflate(R.layout.employee_add_form, null)
            val emailEmpl = emplView.findViewById<View>(R.id.email_e) as EditText
            val nameEmpl = emplView.findViewById<View>(R.id.name_e) as EditText
            val lastNameEmpl = emplView.findViewById<View>(R.id.last_name_e) as EditText
            val postEmpl = emplView.findViewById<View>(R.id.post_e) as EditText
            val avatarEmpl = emplView.findViewById<View>(R.id.avatar_e) as EditText
            AlertDialog.Builder(requireActivity())
                .setView(emplView)
                .setCancelable(false)
                .setPositiveButton("Добавить", DialogInterface.OnClickListener { _, _ ->
                    if (emailEmpl.text.isNotBlank() && nameEmpl.text.isNotBlank() && lastNameEmpl.text.isNotBlank()
                        &&postEmpl.text.isNotBlank()) {

                        viewModel.save(
                            Employee(
                                id = 0,
                                email = emailEmpl.text.toString(),
                                first_name = nameEmpl.text.toString(),
                                last_name = lastNameEmpl.text.toString(),
                                post=postEmpl.text.toString(),
                                avatar = avatarEmpl.text.toString()
                            )
                        )

                    } else {
                        Toast.makeText(requireContext(), "Заполните поля", Toast.LENGTH_LONG)
                            .show();

                    }
                })

                .setNegativeButton("Отмена", null)
                .show()
        }
        employeeAdapter!!.updateEmployeeAdapter(viewModel.recordEmployeeData.value.orEmpty())
    show_employee.setOnClickListener{
            Toast.makeText(
                requireContext(),
                "${viewModel.saveErrorLiveData.value}",
                Toast.LENGTH_LONG
            ).show()
           updateList()
        }



    }
    fun updateList(){
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, EmployeeFragment.newInstance())
        transaction.commit()
    }

    private fun delete(id: Int, it: Int) {

    }

    private fun updateEmpl(idUpdate: Int) {

        }
    companion object {
        fun newInstance(): EmployeeFragment {
            return EmployeeFragment()
        }
    }
}


