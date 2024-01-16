package com.example.roomdao30.AllFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.AllRequestAuthorAdapter
import com.example.roomdao30.AllAdapters.AllRequestProductAdapter
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.AllRelation.ClientWithRating
import com.example.roomdao30.AllRelation.ProductWithRating
import com.example.roomdao30.R
import com.example.roomdao30.putArguments
import kotlinx.android.synthetic.main.all_request_author.*
import kotlinx.android.synthetic.main.all_request_product_rating_fragment.*
import java.io.Serializable

class AllRequestProductsRating: Fragment(R.layout.all_request_product_rating_fragment) {
    private var allRequestProductAdapter: AllRequestProductAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        allRequestProductAdapter = AllRequestProductAdapter { reportString += it }
        with(request_product_list) {
            adapter = allRequestProductAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        val responseListA = requireArguments().getSerializable(LIST_RESPONSE) as List<ProductWithRating>
        allRequestProductAdapter!!.updateRequestProductAdapter(responseListA)

    }
    companion object {
        var reportString: String = ""
        private const val LIST_RESPONSE = "listRatingResponse"
        fun newInstance(ratingRequestList: List<ProductWithRating>): AllRequestProductsRating {
            return AllRequestProductsRating().putArguments {
                putSerializable(LIST_RESPONSE, ratingRequestList as Serializable)
            }
        }
    }
}
