package com.ampersand.myshop.modules.products.presentation.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ampersand.myshop.modules.products.data.remote.ProductsApi
import com.ampersand.myshop.modules.products.domain.model.ProductModel
import com.ampersand.myshop.modules.products.domain.repository.ProductRepository
import com.ampersand.myshop.util.Event
import com.ampersand.myshop.util.EventBus.sendEvent
import com.ampersand.myshop.util.RetroInstance.Companion.getRetroInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//@HiltViewModel
//class ProductsViewModel @Inject constructor(
//    private val productRepository: ProductRepository
//) : ViewModel() {
//
//    private  val _productsViewState = MutableStateFlow(ProductsViewState())
//    val productsViewState = _productsViewState.asStateFlow()
//
//    init {
//        getProducts();
//    }
//    private fun getProducts() {
//        viewModelScope.launch {
//            _productsViewState.update {
//                it.copy(isLoading = true)
//            }
//            productRepository.getProducts()
//                .onRight { products ->
//                    _productsViewState.update {
//                        it.copy(isLoading = false, products = products)
//                    }
//                }.onLeft { error ->
//                    _productsViewState.update {
//                        it.copy(isLoading = false, error = error.error.message)
//                    }
//                    sendEvent(Event.ShowToast(error.error.message))
//                }
//        }
//    }
//}


class ProductsViewModel: ViewModel() {

    var liveDataList: MutableLiveData<List<ProductModel>?> = MutableLiveData()

    fun getProducts(): MutableLiveData<List<ProductModel>?> {
        return liveDataList
    }

    fun makeApiCall(){
        val productsServices = getRetroInstance().create(ProductsApi::class.java)
        val call = productsServices.getProducts()
        call.enqueue(object : Callback<List<ProductModel>> {
            override fun onResponse(call: Call<List<ProductModel>>, response: Response<List<ProductModel>>) {
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                liveDataList.postValue(null)
            }
        })
    }

}


