package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.AllRelation.ClientWithRating
import com.example.roomdao30.AllRelation.ProductWithRating
import com.example.roomdao30.R

class AllRequestProductAdapter(var oNSuccessListener: (allInfo: String) -> Unit) :
    RecyclerView.Adapter<AllRequestProductAdapter.RequestProductHolder>() {
    var requestList: List<ProductWithRating> = emptyList()

    class RequestProductHolder (view: View, var oNSuccessListener: (allInfo: String) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val id_product: TextView = view.findViewById(R.id.product_id)
        private val title_product_rating: TextView = view.findViewById(R.id.title_product)
        private val avatar: TextView = view.findViewById(R.id.avatar_product)
        private val description_product_rating: TextView = view.findViewById(R.id.description_product)
        private val idRatingP: TextView = view.findViewById(R.id.id_rating_product)
        private val login_rating: TextView = view.findViewById(R.id.login_client_rating_product)
        private val id_shop: TextView = view.findViewById(R.id.id_shop_rating_product)
        private val id_product_rating_request: TextView = view.findViewById(R.id.id_product_rating_product)
        private val title_Rating: TextView = view.findViewById(R.id.title_rating_product)
        private val description_Rating: TextView = view.findViewById(R.id.description_rating_product)
        private val star_Rating: TextView = view.findViewById(R.id.star_rating_product)
        protected fun bindMainInfo(
            idProduct: String,
            titleP: String,
            avatarP: String,
            descriptionP: String,
            idRating: String,
            loginRating: String,
            idShop: String,
            idProductR: String,
            titleRating: String,
            desriptionRating: String,
            starRating: String,

            ) {
            id_product.text = idProduct
            title_product_rating.text= titleP
            avatar.text = avatarP
            description_product_rating.text = descriptionP
            idRatingP.text = idRating
            login_rating.text = loginRating
            id_shop.text = idShop
            id_product_rating_request.text =idProductR
            title_Rating.text = titleRating
            description_Rating.text = desriptionRating
            star_Rating.text=starRating

        }

        fun bind(ratingRequstList: ProductWithRating) {

            var count = ratingRequstList.rating.size
            var x: Int = 0

            var s1: String = "│";
            var s2: String = "│";
            var s3: String = "│";
            var s4: String = "│";
            var s5: String = "│";
            var s6: String = "│";
            var s7: String = "│"
            repeat(count) {
                s1 += "${ratingRequstList.rating[x].id.toInt()}" + "│"
                s2 += ratingRequstList.rating[x].login + "│"
                s3 += "${ratingRequstList.rating[x].id_shop.toInt()}" + "│"
                s4 += "${ratingRequstList.rating[x].id_product.toInt()}" + "│"
                s5 += ratingRequstList.rating[x].title + "│"
                s6 += ratingRequstList.rating[x].description + "│"
                s7 += ratingRequstList.rating[x].stars + "│"+"◄ "
                x++
            }
            AllRequestAdapter.report = buildString {
                append(
                    "$s1$s2$s3$s4$s5$s6$s7"
                )
            }
            


            bindMainInfo(
                "►"+"│" + ratingRequstList.product.id + "│",
                "│" + ratingRequstList.product.title + "│",
                "│" + ratingRequstList.product.avatar + "│",
                "│" + ratingRequstList.product.description + "│",
                s1, s2, s3, s4, s5, s6, s7


            )
            AllRequestProductAdapter.allreport = buildString {
                append(
                    "►"+"│" + ratingRequstList.product.id + "│",
                    "│" + ratingRequstList.product.title + "│",
                    "│" + ratingRequstList.product.avatar + "│",
                    "│" + ratingRequstList.product.description + "│"+ report
                )
            }
            oNSuccessListener(
                AllRequestProductAdapter.allreport.toString()
            )
        }

        }



    companion object {
        var allreport = ""
        var report: String? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.request_rating_products_form, parent, false)
        return AllRequestProductAdapter.RequestProductHolder(view, oNSuccessListener)
    }

    override fun onBindViewHolder(holder: RequestProductHolder, position: Int) {
        val request = requestList[position]
        holder.bind(request)
    }

    override fun getItemCount(): Int {
       return requestList.size
    }
    fun updateRequestProductAdapter(newResponse: List<ProductWithRating>) {
        requestList = newResponse
        notifyDataSetChanged()
    }
}