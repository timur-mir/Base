package com.example.roomdao30.AllFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.AllRequestAuthorAdapter
import com.example.roomdao30.AllAdapters.AllRequestCarAdapter
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.AllRelation.EmployeeWithCar
import com.example.roomdao30.R
import com.example.roomdao30.putArguments
import kotlinx.android.synthetic.main.all_request_author.*
import kotlinx.android.synthetic.main.all_request_car.*
import java.io.Serializable

class AllRequestCar:Fragment(R.layout.all_request_car) {
    private var allRequestCarAdapter: AllRequestCarAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        allRequestCarAdapter = AllRequestCarAdapter { AllRequestCar.reportString += it }
        with(request_list_car){
            adapter = allRequestCarAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        val responseListE = requireArguments().getSerializable(AllRequestCar.LIST_RESPONSE) as List<EmployeeWithCar>
        allRequestCarAdapter!!.updateRequestCarAdapter(responseListE)

        send_report_car.setOnClickListener{
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, AllRequestCar.reportString)
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "Отправка отчёта"));
        }


    }






    companion object {
        var reportString: String = ""
        const val LIST_RESPONSE = "listCarResponse"
        fun newInstance(carRequestList: List<EmployeeWithCar>): AllRequestCar{
            return  AllRequestCar().putArguments {
                putSerializable(LIST_RESPONSE,carRequestList as Serializable)
            }

            }
        }
    }
