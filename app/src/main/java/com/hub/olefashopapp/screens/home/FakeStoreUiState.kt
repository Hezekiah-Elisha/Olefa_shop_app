package com.hub.olefashopapp.screens.home

import com.hub.olefashopapp.model.Product

sealed interface FakeStoreUiState {
    data class Success(val products: Product) : FakeStoreUiState
    object Error : FakeStoreUiState
    object Loading : FakeStoreUiState
}