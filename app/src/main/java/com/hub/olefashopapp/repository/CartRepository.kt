package com.hub.olefashopapp.repository

import com.hub.olefashopapp.model.Cart
import com.hub.olefashopapp.network.ProductApiService
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val api: ProductApiService
){
    suspend fun getCart(): Cart = api.getCart()
}