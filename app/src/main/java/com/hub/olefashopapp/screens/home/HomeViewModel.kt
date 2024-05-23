package com.hub.olefashopapp.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hub.olefashopapp.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val fakeStoreRepository: ProductRepository) : ViewModel(){
    var uiState: FakeStoreUiState by mutableStateOf(FakeStoreUiState.Loading)
        private set

    init {
        getProducts()
    }

    fun getProducts(){
        viewModelScope.launch {
            uiState = FakeStoreUiState.Loading
            try {
                uiState = FakeStoreUiState.Success(
                    fakeStoreRepository.getProducts()

                )
                Log.d("fakestoreapp", "getProducts: ${uiState}")
            } catch (e: Exception) {
                uiState = FakeStoreUiState.Error
                Log.d("fakestoreapp", "getProducts exe: ${e.message}")
            } catch (e: HttpException) {
                uiState  = FakeStoreUiState.Error
                Log.d("fakestoreapp", "getProducts http: ${e.message}")
            }
        }

//         Log.d("fakestoreapp", "getProducts: ${uiState}")
    }
}

