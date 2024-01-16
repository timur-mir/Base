package com.example.roomdao30.AllFragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.ClientAdapter
import com.example.roomdao30.AllAdapters.RatingAdapter
import com.example.roomdao30.AllDialogForm.ClientForm
import com.example.roomdao30.AllDialogForm.RatingForm
import com.example.roomdao30.AllDialogForm.RequestRatingForm
import com.example.roomdao30.AllDialogForm.UpdateClientForm
import com.example.roomdao30.AllEntitys.Rating
import com.example.roomdao30.AllViewModels.AddClientViewModel
import com.example.roomdao30.AllViewModels.RatingViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.client_fragment.*
import kotlinx.android.synthetic.main.rating_fragment.*

class RatingFragment : Fragment(R.layout.rating_fragment) {
    private var ratingAdapter: RatingAdapter? = null
    private val viewModel by viewModels<RatingViewModel>()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar_rating.setOnMenuItemClickListener{menuItem->
            when(menuItem.itemId){
                R.id.request->{
                    requestOnTables()
                    true
                }
                else -> false
            }
        }

        ratingAdapter =
            RatingAdapter({ idUpdate -> updateRating(idUpdate) }, { it, id -> delete(id, it) })
        with(rating_list) {
            adapter = ratingAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
                //itemAnimator = ScaleInAnimator()
            }
        }
        viewModel.loadList()
        viewModel.recordRatingData.observe(viewLifecycleOwner) { ratingList ->
            ratingAdapter!!.updateRatingAdapter(ratingList)
        }
        add_rating.setOnClickListener {
            val dialogFragmentForAddRating = RatingForm()
            dialogFragmentForAddRating.oNSuccessListener = { newRating ->
                addRating(newRating)
               rating_list.scrollToPosition(0)
            }
            dialogFragmentForAddRating.show(childFragmentManager, "RatingDialog")

        }
        show_rating_list.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "${viewModel.saveErrorLiveData.value}",
                Toast.LENGTH_LONG
            ).show()
           updateList()

        }

        //ratingAdapter!!.updateRatingAdapter((viewModel.recordRatingData.value.orEmpty()))
    }
    fun updateList(){
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, RatingFragment.newInstance())
        transaction.commit()
    }

    private fun requestOnTables(){
        viewModel.requestRating()
        viewModel.requestRatingList.observe(viewLifecycleOwner) { ratingRequestList ->
//            val transaction: FragmentTransaction =parentFragmentManager.beginTransaction()
//            transaction.add(R.id.container3, AllRequestRating.newInstance(ratingRequestList))
//            transaction.commit()

            val infoFragment = RequestRatingForm(ratingRequestList)

            infoFragment.show(childFragmentManager, "update")
        }
    }

    private fun addRating(rating: Rating) {
        viewModel.save(rating)
        Toast.makeText(
            requireContext(),
            "Добавление оценки клиента с логином :${rating.login.toString()}",
            Toast.LENGTH_LONG
        ).show();

    }



    private fun updateRating(updateId: Int) {

    }

    private fun delete(ratingId: Int, pos: Int) {

    }

    companion object {
        fun newInstance(): RatingFragment {
            return RatingFragment()
        }
    }
}