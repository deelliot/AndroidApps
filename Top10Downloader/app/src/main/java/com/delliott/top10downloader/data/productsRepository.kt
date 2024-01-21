package com.delliott.top10downloader.data

class ProductRepository {
    private val productsApi = RetrofitHelper.getInstance().create(ProductsAPI::class.java)

    suspend fun getAllProducts(limit: Int = -1): List<Product> {
        val response = productsApi.getProducts()
        return if (limit == -1) {
            response.products
        }else {
            response.products.take(limit)
        }
    }
}