package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.Product
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.AllRelation.ProductWithRating
import com.example.roomdao30.AllRepositorys.AuthorRepository
import com.example.roomdao30.AllRepositorys.ProductRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class ProductViewModel:ViewModel() {
    private val productRepository = ProductRepository()

    private val requestRatingsForProduct = MutableLiveData<List<ProductWithRating>>()

    val requestProductList: LiveData<List<ProductWithRating>>
        get() = requestRatingsForProduct
    private val getProductMutableLiveData = MutableLiveData<Product?>()



    val updateProductLiveData: LiveData<Product?>
        get() = getProductMutableLiveData


    private val productsMutableLiveData = MutableLiveData<List<Product>>()

    val recordProductData: LiveData<List<Product>>
        get() = productsMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent

    fun save(product: Product) {
        viewModelScope.launch {
            try {
                if (product.id == 0) {
                    productRepository.saveProduct(product)
                } else {
                    productRepository.updateProduct(product
                    )
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения продукта")
                showError(t)
            }
        }
    }
    fun requestRatingProduct() {
        viewModelScope.launch {
            try {
                requestRatingsForProduct.postValue(productRepository.requestProducts())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в запросе рейтинга $t ")
            }
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            try {
                productRepository.updateProduct(product)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления продукта")
                showError(t)

            }

        }
    }
    fun loadList() {
        viewModelScope.launch {
            try {
                productsMutableLiveData.postValue(productRepository.getAllProducts())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке продукта ")
            }
        }
    }
    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(2150)
    }


}