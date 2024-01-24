package com.delliott.flickrbrowser.data

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val FLICKR_API_KEY = "0d71891f987940a270694539b94f5423"

object ApiServiceProvider {
    private const val baseUrl = "https://api.flickr.com/services/rest/"

    val client: ApiService by lazy {
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().build()
                )
            )
            .build().create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&text=dogs&api_key=$FLICKR_API_KEY")
    suspend fun fetchImages(): PhotosSearchResponse
}
