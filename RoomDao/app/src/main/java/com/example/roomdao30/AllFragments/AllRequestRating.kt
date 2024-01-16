package com.example.roomdao30.AllFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.AllRequestAdapter
import com.example.roomdao30.AllRelation.ClientWithRating
import com.example.roomdao30.R
import com.example.roomdao30.putArguments
import kotlinx.android.synthetic.main.all_request_rating_fragment.*
import java.io.Serializable

class AllRequestRating : Fragment(R.layout.all_request_rating_fragment) {
    private var allRequestAdapter: AllRequestAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        allRequestAdapter = AllRequestAdapter { reportString += it }
        with(request_list) {
            adapter = allRequestAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        val responseList =
            requireArguments().getSerializable(LIST_RESPONSE) as List<ClientWithRating>
        allRequestAdapter!!.updateRequestAdapter(responseList)




        send_report.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, reportString)
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "Отправка отчёта"));
        }

    }

    companion object {
        var reportString: String = ""
        private const val LIST_RESPONSE = "listRatingResponse"
        fun newInstance(ratingRequestList: List<ClientWithRating>): AllRequestRating {
            return AllRequestRating().putArguments {
                putSerializable(LIST_RESPONSE, ratingRequestList as Serializable)
            }
        }
    }
}

