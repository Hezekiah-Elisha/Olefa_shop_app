package com.hub.olefashopapp.model

data class Cart(
    val __v: Int,
    val date: String,
    val id: Int,
    val products: List<ProductX>,
    val userId: Int
)