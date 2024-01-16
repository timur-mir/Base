package com.example.roomdao30.AllFragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.AuthorAdapter
import com.example.roomdao30.AllAdapters.SupplyAdapter
import com.example.roomdao30.AllDialogForm.AuthorForm
import com.example.roomdao30.AllDialogForm.SupplyForm
import com.example.roomdao30.AllEntitys.Supply
import com.example.roomdao30.AllViewModel.SupplyViewModel
import com.example.roomdao30.AllViewModels.AuthorViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.author_fragment.*
import kotlinx.android.synthetic.main.supply_fragment.*

class SupplyFragment: Fragment(R.layout.supply_fragment) {
    private val viewModel by viewModels <SupplyViewModel>()
    private var supplyAdapter: SupplyAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        supplyAdapter = SupplyAdapter()
        with(supply_list) {
            adapter = supplyAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        viewModel.loadList()
        viewModel.recordSupplyData.observe(viewLifecycleOwner) { supplyList ->
            supplyAdapter!!.updateSupplyAdapter(supplyList)
        }
        add_supply.setOnClickListener {
            val dialogFragmentForAddSupply = SupplyForm()
            dialogFragmentForAddSupply.oNSuccessListener = { newSupply ->
                addASupply(newSupply)

            }
            dialogFragmentForAddSupply.show(childFragmentManager, "supplyDialog")

        }
        show_supply_list.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "${viewModel.saveErrorLiveData.value}",
                Toast.LENGTH_LONG
            ).show()
         updateList()
        }


    }
    fun updateList(){
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, SupplyFragment.newInstance())
        transaction.commit()
    }

    private fun addASupply(newSupply: Supply) {
        viewModel.save(newSupply)
        Toast.makeText(
            requireContext(),
            "Добавление данных поставки продукта с номером ${newSupply.id_product}",
            Toast.LENGTH_LONG
        ).show();
    }


    companion object {
        fun newInstance(): SupplyFragment {
            return SupplyFragment()
        }
    }
}