package com.example.roomdao30.AllFragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Provider
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.update_cllient_form.view.*
import kotlinx.android.synthetic.main.update_provider_form.view.*

class UpdateProviderForm(val provForUpdateList: Provider?) : DialogFragment() {
    var oNSuccessListener: ((Provider) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dialogView: View = inflater.inflate(R.layout.update_provider_form, container, false)
        dialogView.enterUpdate.setOnClickListener {
            val nameO = dialogView.organisationProvU.text.toString()
            val addressO = dialogView.addressProvU.text.toString()
            val phoneO = dialogView.phoneProvU.text.toString()

            if (provForUpdateList != null) {

                oNSuccessListener?.let {
                    it(
                        Provider(
                            id_provider = provForUpdateList.id_provider,
                            organization = nameO,
                            address = addressO,
                            phone_number = phoneO
                        )
                    )
                }
            }

            dismiss()

        }
        return dialogView
    }

}
