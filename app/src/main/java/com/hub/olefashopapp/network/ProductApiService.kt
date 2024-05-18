package com.hub.olefashopapp.network

import com.hub.olefashopapp.model.Product
import retrofit2.http.GET

/**
 * Retrofit service object for creating api calls
 */
interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): Product
}