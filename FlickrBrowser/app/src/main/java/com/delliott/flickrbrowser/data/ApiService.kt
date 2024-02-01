package com.delliott.flickrbrowser.data

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val FLICKR_API_KEY = "0d71891f987940a270694539b94f5423"

object ApiServiceProvider {
    private const val baseUrl = "https://api.flickr.com/services/"

    val client: ApiService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY) //prints out requests and responses
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder().baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().build()
                )
            )
            .build().create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("rest")
    suspend fun fetchImages(
        @Query(value = "nojsoncallback") nojsoncallback: String = "1",
        @Query(value = "format") format: String = "json",
        @Query(value = "method") method: String = "flickr.photos.search",
        @Query(value = "api_key") apiKey: String = FLICKR_API_KEY,
        //@Query(value = "per_page") limit: Int = 5,
        @Query(value = "text") searchTerm: String,
    ): PhotosSearchResponse

    @GET("rest")
    suspend fun fetchOwner(
        @Query(value = "nojsoncallback") nojsoncallback: String = "1",
        @Query(value = "format") format: String = "json",
        @Query(value = "method") method: String = "flickr.people.getInfo",
        @Query(value = "api_key") apiKey: String = FLICKR_API_KEY,
        @Query(value = "user_id") userId: String,
    ): OwnerSearchResponse

}