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
import com.example.roomdao30.AllAdapters.CarAdapter
import com.example.roomdao30.AllAdapters.PhoneAdapter
import com.example.roomdao30.AllEntitys.OfficialCar
import com.example.roomdao30.AllEntitys.PhoneNumber
import com.example.roomdao30.AllViewModels.CarViewModel
import com.example.roomdao30.AllViewModels.PhoneNumberViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.car_fragment.*
import kotlinx.android.synthetic.main.phone_number_fragment.*

class PhoneNumberFragment: Fragment(R.layout.phone_number_fragment) {
    private val viewModel by viewModels<PhoneNumberViewModel>()
    private var phoneAdapter:PhoneAdapter?= null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        phoneAdapter=PhoneAdapter()
        with(phone_list) {
            adapter = phoneAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        viewModel.loadList()
        viewModel.recordPhonesData.observe(viewLifecycleOwner) { phone->
            phoneAdapter!!.updatePhones(phone)
        }
        add_phone.setOnClickListener{
            val li = LayoutInflater.from(requireActivity())
            val phoneViewClient: View = li.inflate(R.layout.phone_add_form, null)
           val phoneClient = phoneViewClient.findViewById<View>(R.id.number_client) as EditText
            val loginClient = phoneViewClient.findViewById<View>(R.id.login_client_ph) as EditText
            val emailClient = phoneViewClient.findViewById<View>(R.id.email_client_phone) as EditText
            AlertDialog.Builder(requireActivity())
                .setView(phoneViewClient)
                .setCancelable(false)
                .setPositiveButton("Добавить", DialogInterface.OnClickListener { _, _ ->
                    if (phoneClient.text.isNotBlank()&&loginClient.text.isNotBlank() ) {

                        viewModel.save(
                           PhoneNumber(
                               phonenumb = phoneClient.text.toString(),
                               logincl = loginClient.text.toString(),
                                email = emailClient.text.toString())
                        )

                    } else {
                        Toast.makeText(requireContext(), "Заполните поле", Toast.LENGTH_LONG)
                            .show();

                    }
                })

                .setNegativeButton("Отмена", null)
                .show()
        }
        show_phone_list.setOnClickListener {

            updateList()
        }

    }

    fun updateList(){
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, PhoneNumberFragment.newInstance())
        transaction.commit()
    }


    companion object {
        fun newInstance(): PhoneNumberFragment {
            return PhoneNumberFragment()
        }
    }
}