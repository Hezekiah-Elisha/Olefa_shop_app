package com.hub.olefashopapp.screens.cart

import com.hub.olefashopapp.model.Cart

interface CartState {
    object Loading : CartState
    data class Success(val cart: Cart) : CartState
    object Error : CartState
}