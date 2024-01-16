package com.example.roomdao30.AllFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.roomdao30.AllViewModels.AuthorViewModel
import com.example.roomdao30.AllViewModels.CarViewModel
import com.example.roomdao30.AllViewModels.ProductViewModel
import com.example.roomdao30.AllViewModels.RatingViewModel
import com.example.roomdao30.Navigator
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment:Fragment(R.layout.main_fragment),Navigator {
    private val viewModel by viewModels<RatingViewModel>()
    private val viewModelA by viewModels<AuthorViewModel>()
    private val viewModelP by viewModels<ProductViewModel>()
    private val viewModelC by viewModels<CarViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        clientfrag.setOnClickListener {
            navigateTo(ClientFragment.newInstance())
        }
        employeefrag.setOnClickListener {
            navigateTo(EmployeeFragment.newInstance())
        }
        rating.setOnClickListener {
            navigateTo(RatingFragment.newInstance())
        }
        ratingRequestB.setOnClickListener {
            viewModel.requestRating()
            viewModel.requestRatingList.observe(viewLifecycleOwner) { ratingRequestList ->
                navigateTo(AllRequestRating.newInstance(ratingRequestList))
            }
        }
        favourite_author.setOnClickListener {
            navigateTo(AuthorFragment.newInstance())
        }
        requestAuthors.setOnClickListener {
            viewModelA.requestAuthors()
            viewModelA.requestAuthorList.observe(viewLifecycleOwner) { authorRequestList ->
                navigateTo(AllRequestAuthor.newInstance(authorRequestList))
            }
        }
        product.setOnClickListener {
            navigateTo(ProductFragment.newInstance())
        }
        product_request.setOnClickListener {
            viewModelP.requestRatingProduct()
            viewModelP.requestProductList.observe(viewLifecycleOwner) { productRequestList ->
                navigateTo(AllRequestProductsRating.newInstance(productRequestList))
            }
        }
        shop.setOnClickListener {
            navigateTo(BookShopFragment.newInstance())
        }
        car.setOnClickListener {
            navigateTo(CarFragment.newInstance())
        }
        request_employee_car.setOnClickListener {
            viewModelC.requestCars()
            viewModelC.requestCarList.observe(viewLifecycleOwner) { carRequestList ->
                navigateTo(AllRequestCar.newInstance(carRequestList))
            }
        }
        sale.setOnClickListener {
            navigateTo(SaleFragment.newInstance())
        }
        request_employee_sale.setOnClickListener{
            navigateTo(AllRequestEmployeeAndProduct.newInstance())
        }
        providerfrag.setOnClickListener{
            navigateTo(ProviderFragment.newInstance())
        }
        supply.setOnClickListener{
            navigateTo(SupplyFragment.newInstance())
        }
        request_product_supply.setOnClickListener{
            navigateTo(ProvidersSupplyProducts.newInstance())
        }
        price.setOnClickListener{
            navigateTo(PricesFragment.newInstance())
        }
        purchases_prises.setOnClickListener{
          navigateTo(PurchasesPricesFragment.newInstance())
        }
        purchases.setOnClickListener{
          navigateTo(Purchases.newInstance())
        }
        purchase_status.setOnClickListener{
            navigateTo(PurchaseStatuseFragment.newInstance())
        }
        phone_button.setOnClickListener{
            navigateTo(PhoneNumberFragment.newInstance())
        }
    }


    override fun navigateTo(fragment: Fragment) {
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
}

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }



}