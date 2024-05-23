package com.hub.olefashopapp.screens.cart

import com.hub.olefashopapp.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) {
    var uiState: CartState = CartState.Loading
        private set
}