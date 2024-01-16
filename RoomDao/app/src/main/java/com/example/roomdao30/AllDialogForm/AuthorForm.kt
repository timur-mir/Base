package com.example.roomdao30.AllDialogForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.author_add_form.view.*
import kotlinx.android.synthetic.main.client_add_form.view.*
import kotlinx.android.synthetic.main.client_add_form.view.button_enter

class AuthorForm : DialogFragment() {
    private lateinit var author: Author
    var oNSuccessListener: ((Author) -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView: View = inflater.inflate(R.layout.author_add_form, container, false)
        dialogView.button_enter.setOnClickListener {
            val nameAuthorV = dialogView.name_author.text.toString()

            author = Author( id = 0,nameAuthor = nameAuthorV)
            oNSuccessListener?.let {
                it(author)
            }
            dismiss()
        }
        return dialogView
    }
}