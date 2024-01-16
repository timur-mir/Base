package com.example.roomdao30.AllDialogForm

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Purchases
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.add_purchase_form.*
import kotlinx.android.synthetic.main.add_purchase_form.view.*
import kotlinx.android.synthetic.main.author_add_form.view.*
import kotlinx.android.synthetic.main.client_add_form.view.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class PurchaseForm : DialogFragment() {
    private lateinit var purchase: Purchases
    var createAtInstant: Instant? = null
    var payAtInstant: Instant? = null
    lateinit var localDateTimeThis: LocalDateTime
    var oNSuccessListener: ((Purchases) -> Unit)? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dVpurchase: View = inflater.inflate(R.layout.add_purchase_form, container, false)
        dVpurchase.bdategr.setOnClickListener {
            create_atPF.text=LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toString()
            localDateTimeThis = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
            createAtInstant = localDateTimeThis.toInstant(ZoneOffset.UTC)
        }
        dVpurchase.bdatepay.setOnClickListener {
           pay_atPF.text= LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toString()
            localDateTimeThis = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
            payAtInstant = localDateTimeThis.toInstant(ZoneOffset.UTC)
        }
        dVpurchase.addPurchaseBut.setOnClickListener {
            val idClient = dVpurchase.id_clientP_F.text.toString().toInt()
            val statuseP = dVpurchase.id_purchaseSt_F.text.toString().toInt()
            if (idClient!=0&&statuseP!=0) {

                purchase = Purchases(
                    id = 0,
                    client_id = idClient,
                    statuse_id = statuseP,
                    created_at_purch = createAtInstant ?: Instant.now(),
                    payed_at = payAtInstant ?: Instant.now()
                )
                oNSuccessListener?.let {
                    it(purchase)
                }
                dismiss()
            }
            else {
                Toast.makeText(requireContext(), "Заполните поля", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
        }
        return dVpurchase
    }
}