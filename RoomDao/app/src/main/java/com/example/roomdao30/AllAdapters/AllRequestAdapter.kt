package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllRelation.ClientWithRating
import com.example.roomdao30.R

class AllRequestAdapter(var oNSuccessListener: (allInfo: String) -> Unit) :
    RecyclerView.Adapter<AllRequestAdapter.RequestHolder>() {

    var requestList: List<ClientWithRating> = emptyList()

    companion object {
        var allreport = ""
        var report: String? = null
    }

    class RequestHolder(view: View, var oNSuccessListener: (allInfo: String) -> Unit) :
        RecyclerView.ViewHolder(view) {

        private val login: TextView = view.findViewById(R.id.login_client_request)
        private val name: TextView = view.findViewById(R.id.name_client_request)
        private val last_name: TextView = view.findViewById(R.id.lastname_client_request)
        private val id: TextView = view.findViewById(R.id.id_rating_request)
        private val login_rating: TextView = view.findViewById(R.id.login_client_rating)
        private val id_shop: TextView = view.findViewById(R.id.id_shop_rating_request)
        private val id_product: TextView = view.findViewById(R.id.id_product_rating_request)
        private val titleRating: TextView = view.findViewById(R.id.title_rating_request)
        private val descriptionRating: TextView = view.findViewById(R.id.description_rating_request)
        private val starRating: TextView = view.findViewById(R.id.star_rating_request)
        private val buttonx: Button = view.findViewById(R.id.button_enter_rating)

        protected fun bindMainInfo(
            loginClient: String,
            firstName: String,
            lastName: String,
            idRating: String,
            loginClientRequst: String,
            idShop: String,
            idProduct: String,
            title: String,
            description: String,
            star: String
        ) {
            login.text = loginClient
            name.text = firstName
            last_name.text = lastName
            id.text = idRating.toString()
            login_rating.text = loginClientRequst
            id_shop.text = idShop.toString()
            id_product.text = idProduct.toString()
            titleRating.text = title
            descriptionRating.text = description
            starRating.text = star
            buttonx.isVisible = false
        }

        fun bind(ratingRequstList: ClientWithRating, sizeList: Int) {
            var count = ratingRequstList.ratingRequest.size
            var x: Int = 0

            var s1: String = "│";
            var s2: String = "│";
            var s3: String = "│";
            var s4: String = "│";
            var s5: String = "│";
            var s6: String = "│";
            var s7: String = "│"

            repeat(count) {
                s1 += "${ratingRequstList.ratingRequest[x].id.toInt()}" + "│"
                s2 += ratingRequstList.ratingRequest[x].login + "│"
                s3 += "${ratingRequstList.ratingRequest[x].id_shop.toInt()}" + "│"
                s4 += "${ratingRequstList.ratingRequest[x].id_product.toInt()}" + "│"
                s5 += ratingRequstList.ratingRequest[x].title + "│"
                s6 += ratingRequstList.ratingRequest[x].description + "│"
                s7 += ratingRequstList.ratingRequest[x].stars + "│"+"◄ "
                x++
            }
            report = buildString {
                append(
                    "$s1$s2$s3$s4$s5$s6$s7"
                )
            }

            bindMainInfo(
                "│" + ratingRequstList.login + "│",
                "►"+"│" + ratingRequstList.first_name + "│",
                "│" + ratingRequstList.last_name + "│",
                s1, s2, s3, s4, s5, s6, s7
            )
            allreport = buildString {
                append(
                    "►"+"│" + ratingRequstList.login + "│" +
                            "│" + ratingRequstList.first_name + "│" +
                            "│" + ratingRequstList.last_name + "│" + report
                )
            }
            oNSuccessListener(
                allreport
            )
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.request_rating_form, parent, false)
        return AllRequestAdapter.RequestHolder(view, oNSuccessListener)
    }

    override fun onBindViewHolder(holder: RequestHolder, position: Int) {
        val request = requestList[position]
        val sizeList = itemCount
        holder.bind(request, sizeList)
    }

    override fun getItemCount(): Int {
        return requestList.size
    }

    fun updateRequestAdapter(newResponse: List<ClientWithRating>) {
        requestList = newResponse
        notifyDataSetChanged()
    }

}