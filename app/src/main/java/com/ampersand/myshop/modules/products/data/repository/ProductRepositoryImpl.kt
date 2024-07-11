package com.ampersand.myshop.modules.products.data.repository

import arrow.core.Either
import com.ampersand.myshop.modules.products.data.mapper.toNetworkError
import com.ampersand.myshop.modules.products.data.remote.ProductsApi
import com.ampersand.myshop.modules.products.domain.model.NetworkError
import com.ampersand.myshop.modules.products.domain.model.ProductModel
import com.ampersand.myshop.modules.products.domain.repository.ProductRepository
import com.ampersand.myshop.util.RetroInstance.Companion.getRetroInstance

class ProductRepositoryImpl: ProductRepository {
    override suspend fun getProducts(): Either<NetworkError, List<ProductModel>> {
        return Either.catch {
            getRetroInstance().create(ProductsApi::class.java).getProducts()
        }.mapLeft { it.toNetworkError() }
    }
}