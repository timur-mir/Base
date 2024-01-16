package com.example.roomdao30.AllDialogForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.client_add_form.view.*

class ClientForm : DialogFragment() {
    private lateinit var client: Client
    var oNSuccessListener: ((Client) -> Unit)? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView: View = inflater.inflate(R.layout.client_add_form, container, false)

        dialogView.button_enter.setOnClickListener {
            val nameC = dialogView.name_c.text.toString()
            val lastNameC = dialogView.last_name_c.text.toString()
            val emailC = dialogView.email_c.text.toString()
            val loginC = dialogView.login_c.text.toString()
            val passwordC = dialogView.password_c.text.toString()
            val avatarC = dialogView.avatar_c.text.toString()


            client = Client(
                id = 0,
                first_name = nameC,
                last_name = lastNameC,
                email = emailC,
                login = loginC,
                password = passwordC,
                avatar = avatarC
            )
            oNSuccessListener?.let {
                it(client)
            }
            dismiss()
        }
        return dialogView
    }


}