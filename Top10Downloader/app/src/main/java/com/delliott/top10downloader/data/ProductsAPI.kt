package com.delliott.top10downloader.data

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


object RetrofitHelper {
   private const val baseUrl = "https://dummyjson.com/products/"
    fun getInstance(): Retrofit {
        val moshi = Moshi.Builder()
            .build()
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}
interface ProductsAPI {
    @GET("/products")
    suspend fun getProducts(): ProductResponse
}
