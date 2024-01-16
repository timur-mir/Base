package com.example.roomdao30.AllFragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.AuthorAdapter
import com.example.roomdao30.AllAdapters.CarAdapter
import com.example.roomdao30.AllEntitys.OfficialCar
import com.example.roomdao30.AllViewModels.AuthorViewModel
import com.example.roomdao30.AllViewModels.CarViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.author_fragment.*
import kotlinx.android.synthetic.main.car_fragment.*

class CarFragment: Fragment(R.layout.car_fragment) {
    private val viewModel by viewModels <CarViewModel>()
    private var carAdapter: CarAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        carAdapter = CarAdapter()
        with(cars_list) {
            adapter = carAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        viewModel.loadList()
        viewModel.recordCarData.observe(viewLifecycleOwner) { carList ->
            carAdapter!!.updateCarAdapter(carList)
        }
        add_car.setOnClickListener{
            val li = LayoutInflater.from(requireActivity())
            val carView: View = li.inflate(R.layout.car_add_form, null)
//            val titleCar = carView.findViewById<View>(R.id.main_title) as TextView
            val brandCar = carView.findViewById<View>(R.id.brand_car) as EditText
            AlertDialog.Builder(requireActivity())
                .setView(carView)
                .setCancelable(false)
                .setPositiveButton("Добавить", DialogInterface.OnClickListener { _, _ ->
                    if (brandCar.text.isNotBlank() ) {

                        viewModel.save(
                            OfficialCar(
                                id = 0,
                                car_brand = brandCar.text.toString(),

                            )
                        )

                    } else {
                        Toast.makeText(requireContext(), "Заполните поле", Toast.LENGTH_LONG)
                            .show();

                    }
                })

                .setNegativeButton("Отмена", null)
                .show()
        }
      show_cars_list.setOnClickListener {

          updateList()
        }



    }
    fun updateList(){
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, CarFragment.newInstance())
        transaction.commit()
    }

    companion object {
        fun newInstance(): CarFragment {
            return CarFragment()
        }
    }
}