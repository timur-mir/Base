package com.example.roomdao30.AllDialogForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.client_add_form.view.*
import kotlinx.android.synthetic.main.client_add_form.view.email_c
import kotlinx.android.synthetic.main.client_add_form.view.name_c
import kotlinx.android.synthetic.main.update_cllient_form.view.*

class UpdateClientForm(private val clientForUpdate: Client?):DialogFragment(){
    lateinit var clientUp: Client
    var oNSuccessListener: ((Client) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dialogView: View = inflater.inflate(R.layout.update_cllient_form, container, false)
        if (clientForUpdate != null) {
            dialogView.name_c2.setText(clientForUpdate.first_name.toString())
        }
        if (clientForUpdate != null) {
            dialogView.last_name_c2.setText(clientForUpdate.last_name.toString())
        }
        if (clientForUpdate != null) {
            dialogView.email_c2.setText(clientForUpdate.email.toString())
        }
        if (clientForUpdate != null) {
            dialogView.login_c2.setText(clientForUpdate.login.toString())
        }
        if (clientForUpdate != null) {
            dialogView.password_c2.setText(clientForUpdate.password.toString())
        }
        if (clientForUpdate != null) {
            dialogView.avatar_c2.setText(clientForUpdate.avatar.toString())
        }
        dialogView.button_enter2.setOnClickListener {

        val nameC = dialogView.name_c2.text.toString()
        val lastNameC = dialogView.last_name_c2.text.toString()
        val emailC = dialogView.email_c2.text.toString()
        val loginC = dialogView.login_c2.text.toString()
        val passwordC = dialogView.password_c2.text.toString()
        val avatarC = dialogView.avatar_c2.text.toString()


            if (clientForUpdate != null) {
                clientUp = Client(
                    id = clientForUpdate.id,
                    first_name = nameC,
                    last_name = lastNameC,
                    email = emailC,
                    login = loginC,
                    password = passwordC,
                    avatar = avatarC
                )
            }
        oNSuccessListener?.let {
            it(clientUp)
        }
        dismiss()
        }
        return dialogView
    }


}
