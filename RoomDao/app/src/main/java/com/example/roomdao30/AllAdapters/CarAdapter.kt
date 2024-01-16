package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.OfficialCar
import com.example.roomdao30.R

class CarAdapter(): RecyclerView.Adapter<CarAdapter.CarHolder>() {
    private var cars: List<OfficialCar> = emptyList()

    class CarHolder(view: View):RecyclerView.ViewHolder(view){
        private val idCar: TextView = view.findViewById(R.id.id_car)
        private val brandCar: TextView = view.findViewById(R.id.brand)
        fun bind(car:OfficialCar) {
            idCar.text=car.id.toString()
           brandCar.text=car.car_brand.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_car, parent, false)
        return CarAdapter.CarHolder(view)
    }

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        val car = cars[position]
        holder.bind(car)
    }

    override fun getItemCount(): Int {
     return cars.size
    }
    fun updateCarAdapter(newList: List<OfficialCar>) {
        cars = newList
        notifyDataSetChanged()
    }

}
