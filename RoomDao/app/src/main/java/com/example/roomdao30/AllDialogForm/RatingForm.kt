package com.example.roomdao30.AllDialogForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Rating
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.client_add_form.view.*
import kotlinx.android.synthetic.main.client_add_form.view.button_enter
import kotlinx.android.synthetic.main.rating_add_form.view.*

class RatingForm : DialogFragment() {
    private lateinit var rating: Rating
    var oNSuccessListener: ((Rating) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView: View = inflater.inflate(R.layout.rating_add_form, container, false)

        dialogView.button_enter_rating.setOnClickListener {
            val loginC = dialogView.login_C.text.toString()
            val shopBR = dialogView.shop_B_R.text.toString().toInt()
            val productB = dialogView.product_B.text.toString().toInt()
            val titleB = dialogView.title_B.text.toString()
            val descriptionB = dialogView.description_B.text.toString()
            val starV = dialogView.star_V.text.toString()


            rating = Rating(
                id = 0,
                login = loginC,
                id_shop = shopBR,
                id_product = productB,
                title = titleB,
                description = descriptionB,
                stars = starV
            )
            oNSuccessListener?.let {
                it(rating)
            }
            dismiss()
        }
        return dialogView
    }
}