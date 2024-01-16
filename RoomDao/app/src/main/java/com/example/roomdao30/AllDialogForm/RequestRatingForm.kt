package com.example.roomdao30.AllDialogForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.roomdao30.AllRelation.ClientWithRating
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.request_rating_form.view.*

class RequestRatingForm(val ratingResponse: List<ClientWithRating>) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView: View = inflater.inflate(R.layout.request_rating_form, container, false)
        dialogView.name_client_request.setText(ratingResponse[0].first_name)
        dialogView.lastname_client_request.setText(ratingResponse[0].last_name)
        dialogView.login_client_rating.setText(ratingResponse[0].login)
        dialogView.id_rating_request.setText(ratingResponse[0].ratingRequest[0].id.toString())
        dialogView.login_client_rating.setText(ratingResponse[0].ratingRequest[0].login)
        dialogView.id_shop_rating_request.setText(ratingResponse[0].ratingRequest[0].id_shop.toString())
        dialogView.id_product_rating_request.setText(ratingResponse[0].ratingRequest[0].id_product.toString())
        dialogView.title_rating_request.setText(ratingResponse[0].ratingRequest[0].title)
        dialogView.description_rating_request.setText(ratingResponse[0].ratingRequest[0].description)
        dialogView.star_rating_request.setText(ratingResponse[0].ratingRequest[0].stars)
        dialogView.button_enter_rating.setOnClickListener {

            dismiss()

        }

        return dialogView
    }
}
