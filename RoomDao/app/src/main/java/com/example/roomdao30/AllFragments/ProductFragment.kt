package com.example.roomdao30.AllFragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.AuthorAdapter
import com.example.roomdao30.AllAdapters.ProductAdapter
import com.example.roomdao30.AllEntitys.Product
import com.example.roomdao30.AllViewModels.AuthorViewModel
import com.example.roomdao30.AllViewModels.ProductViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.author_fragment.*
import kotlinx.android.synthetic.main.product_fragment.*

class
ProductFragment : Fragment(R.layout.product_fragment) {
    private val viewModel by viewModels<ProductViewModel>()
    private var productAdapter: ProductAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productAdapter = ProductAdapter()
        with(product_list) {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }

    viewModel.loadList()
    viewModel.recordProductData.observe(viewLifecycleOwner) { productList ->
        productAdapter!!.updateProductAdapter(productList)}

        add_product.setOnClickListener {
            val li = LayoutInflater.from(requireActivity())
            val prodView: View = li.inflate(R.layout.product_add_form, null)
            val titleProduct = prodView.findViewById<View>(R.id.title_PF) as EditText
            val avatarProduct = prodView.findViewById<View>(R.id.avatar_PF) as EditText
            val descriptionProduct = prodView.findViewById<View>(R.id.description_PF) as EditText
            AlertDialog.Builder(requireActivity())
                .setView(prodView)
                .setCancelable(false)
                .setPositiveButton("Добавить", DialogInterface.OnClickListener { _, _ ->
                    if (titleProduct.text.isNotBlank() && avatarProduct.text.isNotBlank() && descriptionProduct.text.isNotBlank()) {

                        viewModel.save(
                            Product(
                                id = 0,
                                title = titleProduct.text.toString(),
                                avatar = avatarProduct.text.toString(),
                                description = descriptionProduct.text.toString()
                            )
                        )

                    } else {
                        Toast.makeText(requireContext(), "Заполните поля", Toast.LENGTH_LONG)
                            .show();

                    }
                })

                .setNegativeButton("Отмена", null)
                .show()
        }
       productAdapter!!.updateProductAdapter(viewModel.recordProductData.value.orEmpty())

            show_product_list.setOnClickListener {
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
        transaction.replace(R.id.container, ProductFragment.newInstance())
        transaction.commit()
    }


    companion object {
        fun newInstance(): ProductFragment {
            return ProductFragment()
        }
    }
}