package com.ampersand.myshop.modules.products.presentation.screens


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ampersand.myshop.modules.products.data.repository.ProductRepositoryImpl
import com.ampersand.myshop.modules.products.domain.model.ProductModel
import com.ampersand.myshop.modules.products.domain.repository.ProductRepository
import com.ampersand.myshop.util.Event
import com.ampersand.myshop.util.EventBus.sendEvent
import kotlinx.coroutines.launch


class ProductsViewModel: ViewModel() {

    private val products: MutableLiveData<List<ProductModel>> = MutableLiveData()
    private val productRepository: ProductRepository = ProductRepositoryImpl()

    fun getProducts(): MutableLiveData<List<ProductModel>> {
        return products
    }

    fun makeApiCall(){
        viewModelScope.launch {
            productRepository.getProducts()
                .onRight { it ->
                    products.postValue(it)
                }.onLeft { error ->
                    sendEvent(Event.ShowToast(error.error.message))
                }
        }
    }

}


