package com.delliott.top10downloader.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delliott.top10downloader.data.Product
import com.delliott.top10downloader.data.ProductRepository
import com.delliott.top10downloader.data.ProductsAPI
import com.delliott.top10downloader.data.RetrofitHelper
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val productsApi = RetrofitHelper.getInstance().create(ProductsAPI::class.java)
    private val _products = MutableLiveData<List<Product>>()
    private val productRepository = ProductRepository()
    val products: LiveData<List<Product>>
        get() = _products
    fun loadProducts() {
        viewModelScope.launch {
            val products = productRepository.getAllProducts()
            _products.value = products
        }
    }
}
