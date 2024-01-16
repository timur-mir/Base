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
import com.example.roomdao30.AllAdapters.ProductAdapter
import com.example.roomdao30.AllAdapters.ShopAdapter
import com.example.roomdao30.AllEntitys.BookShop
import com.example.roomdao30.AllEntitys.Product
import com.example.roomdao30.AllViewModels.BookShopViewModel
import com.example.roomdao30.AllViewModels.ProductViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.book_shop_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.product_fragment.*

class BookShopFragment : Fragment(R.layout.book_shop_fragment) {
    private val viewModel by viewModels<BookShopViewModel>()
    private var shopAdapter: ShopAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        shopAdapter = ShopAdapter()
        with(shop_list) {
            adapter = shopAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        viewModel.loadList()
        viewModel.recordShopData.observe(viewLifecycleOwner) { shopList ->
           shopAdapter!!.updateShopAdapter(shopList)}

        add_shop.setOnClickListener {
            val li = LayoutInflater.from(requireActivity())
            val shopView: View = li.inflate(R.layout.shop_add_form, null)
            val titleShop = shopView.findViewById<View>(R.id.title_SH) as EditText
            val addressShop = shopView.findViewById<View>(R.id.address_SH) as EditText
            val phoneShop = shopView.findViewById<View>(R.id.phone_SH) as EditText
            AlertDialog.Builder(requireActivity())
                .setView(shopView)
                .setCancelable(false)
                .setPositiveButton("Добавить", DialogInterface.OnClickListener { _, _ ->
                    if (titleShop.text.isNotBlank() && addressShop.text.isNotBlank() && phoneShop.text.isNotBlank()) {

                        viewModel.save(
                            BookShop(
                                id = 0,
                                title = titleShop.text.toString(),
                                address = addressShop.text.toString(),
                                phone_number = phoneShop.text.toString()
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
       shopAdapter!!.updateShopAdapter(viewModel.recordShopData.value.orEmpty())
        show_shop_list.setOnClickListener {
//            show_product_list.setOnClickListener {
//                Toast.makeText(
//                    requireContext(),
//                    "${viewModel.saveErrorLiveData.value}",
//                    Toast.LENGTH_LONG
//                ).show()
               updateList()
            }


        }
    fun updateList(){
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, BookShopFragment.newInstance())
        transaction.commit()
    }

    companion object {
        fun newInstance(): BookShopFragment {
            return BookShopFragment()
        }
    }

}