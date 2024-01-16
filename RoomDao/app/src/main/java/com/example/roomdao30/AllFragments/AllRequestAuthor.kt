package com.example.roomdao30.AllFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.AllRequestAdapter
import com.example.roomdao30.AllAdapters.AllRequestAuthorAdapter
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.AllRelation.ClientWithRating
import com.example.roomdao30.R
import com.example.roomdao30.putArguments
import kotlinx.android.synthetic.main.all_request_author.*
import kotlinx.android.synthetic.main.all_request_rating_fragment.*
import java.io.Serializable

class AllRequestAuthor : Fragment(R.layout.all_request_author) {
    private var allRequestAuthorAdapter: AllRequestAuthorAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        allRequestAuthorAdapter = AllRequestAuthorAdapter { reportString += it }
        with(request_list_author) {
            adapter = allRequestAuthorAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        val responseListA = requireArguments().getSerializable(AllRequestAuthor.LIST_RESPONSE) as List<ClientWithAuthor>
        allRequestAuthorAdapter!!.updateRequestAuthorAdapter(responseListA)

        send_report_author.setOnClickListener{
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, AllRequestAuthor.reportString)
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "Отправка отчёта"));
        }
    }

    companion object {
        var reportString: String = ""
        const val LIST_RESPONSE = "listAuthorResponse"
        fun newInstance(authorRequestList: List<ClientWithAuthor>): AllRequestAuthor {
            return AllRequestAuthor().putArguments {
                putSerializable(LIST_RESPONSE, authorRequestList as Serializable)
            }
        }
    }
}