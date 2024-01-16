package com.example.roomdao30.AllDialogForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.roomdao30.AllEntitys.Supply
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.supply_add_form.view.*

class SupplyForm : DialogFragment() {
    private lateinit var supply: Supply
    var oNSuccessListener: ((Supply) -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView: View = inflater.inflate(R.layout.supply_add_form, container, false)
        dialogView.button_enterF.setOnClickListener {
            val sizeSupply:Int = dialogView.supply_sizeF.text.toString().toInt()
            val idProvider:Int = dialogView.id_providerF.text.toString().toInt()
            val idProduct:Int = dialogView.id_productF.text.toString().toInt()
            if ((sizeSupply != 0)&&(idProvider != 0)&&(idProduct != 0)) {
                supply = Supply(
                    id = 0,
                    supply_size = sizeSupply,
                    id_provider = idProvider,
                    id_product = idProduct
                )
                oNSuccessListener?.let {
                    it(supply)
                }
                dismiss()
            }
            else {
                Toast.makeText(requireContext(), "Заполните поля", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
        }
        return dialogView
    }
}