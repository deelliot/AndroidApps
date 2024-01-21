package com.delliott.top10downloader.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = true)
data class ProductResponse (
    val products: List<Product>,
)

@JsonClass (generateAdapter = true)
data class Product(
    val id: Int,
    @Json(name = "title")val name: String,
    val description: String,
    val price: Int,
    val rating: Float,
    val thumbnail: String
)
