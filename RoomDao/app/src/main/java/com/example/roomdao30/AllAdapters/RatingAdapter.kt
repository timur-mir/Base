package com.example.roomdao30.AllAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomdao30.AllEntitys.Client
import com.example.roomdao30.AllEntitys.Rating
import com.example.roomdao30.R

class RatingAdapter(
    private val OnItemClick: (idRatingRez: Int) -> Unit,
    private val OnLongClick: (position: Int, idRatingRezDel:Int) -> Unit)
    : RecyclerView.Adapter<RatingAdapter.RatingHolder>() {
    private var ratings: List<Rating> = emptyList()

    class RatingHolder(
        view: View,
        OnItemClick: (idRatingRez: Int) -> Unit,
        OnLongClick: (position: Int, idRatingRezDel: Int) -> Unit,
        private var currentIdRating: Int? = null
    ) : RecyclerView.ViewHolder(view) {
        private val idRating: TextView = view.findViewById(R.id.id_rating)
        private val loginClientRating: TextView = view.findViewById(R.id.login_client_rating)
        private val idShopRating: TextView = view.findViewById(R.id.id_shop_rating)
        private val idProductRating: TextView = view.findViewById(R.id.id_product_rating)
        private val titleRating: TextView = view.findViewById(R.id.title_rating)
        private val descriptionRating: TextView = view.findViewById(R.id.description_rating)
        private val starRating: TextView = view.findViewById(R.id.star_rating)

        init {
            view.setOnClickListener {
                OnItemClick(currentIdRating!!)
            }

            view.setOnLongClickListener {
                OnLongClick(adapterPosition, currentIdRating!!)
                true
            }
        }

        protected fun bindMainInfo(
            idR: Int,
            loginR: String,
            shopR: String,
            productR: String,
            titleR: String,
            descriptionR: String,
            starR: String?
        ) {
            idRating.text = idR.toString()
            loginClientRating.text = loginR.toString()
            idShopRating.text = shopR.toString()
            idProductRating.text = productR.toString()
            titleRating.text = titleR.toString()
            descriptionRating.text = descriptionR.toString()
            starRating.text = starR.toString()
            currentIdRating = idR.toString().toInt()


        }
        fun bind(rating: Rating) {
            bindMainInfo(
                rating.id,
               rating.login,
                rating.id_shop.toString(),
                rating.id_product.toString(),
               rating.title.toString(),
                rating.description.toString(),
                rating.stars.toString()
                           )
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingAdapter.RatingHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_rating, parent, false)
        return RatingAdapter.RatingHolder(view, OnItemClick, OnLongClick)
    }

    override fun onBindViewHolder(holder: RatingAdapter.RatingHolder, position: Int) {
        val rating = ratings[position]
        holder.bind(rating)
    }

    override fun getItemCount(): Int {
        return ratings.size
    }
    fun updateRatingAdapter(newRatings: List<Rating>) {
        ratings = newRatings
        notifyDataSetChanged()
    }

}