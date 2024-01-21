package com.delliott.top10downloader.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delliott.top10downloader.data.Product
import com.delliott.top10downloader.data.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    private val productRepository = ProductRepository()
    private val _title = MutableLiveData<String>()
    val products: LiveData<List<Product>>
        get() = _products //whatever is in _products is copied here
    val title: LiveData<String>
        get() = _title

    fun loadProducts(limit: Int = -1) {
        viewModelScope.launch {
            val products = productRepository.getProducts(limit)
            _products.value = products
        }
    }

    fun allProductsSelected() {
        loadProducts()
        _title.value = "All Products"
    }

    fun top3Selected() {
        loadProducts(3)
        _title.value = "Top 3 Products"
    }

    fun top25Selected() {
        loadProducts(25)
        _title.value = "Top 25 Products"
    }
}

