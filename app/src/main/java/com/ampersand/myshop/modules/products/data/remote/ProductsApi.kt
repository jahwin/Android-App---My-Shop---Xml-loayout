package com.ampersand.myshop.modules.products.data.remote

import com.ampersand.myshop.modules.products.domain.model.ProductModel
import com.ampersand.myshop.util.Constant.PRODUCT_LIST
import retrofit2.Call
import retrofit2.http.GET

interface ProductsApi {

    @GET(PRODUCT_LIST)
    fun getProducts(): Call<List<ProductModel>>
}