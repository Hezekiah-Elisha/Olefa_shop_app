package com.hub.olefashopapp.repository

import com.hub.olefashopapp.model.Product
import com.hub.olefashopapp.network.ProductApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productApiService: ProductApiService
) {
    suspend fun getProducts(): Product = productApiService.getProducts()
}