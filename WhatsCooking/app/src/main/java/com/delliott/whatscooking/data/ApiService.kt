package com.delliott.whatscooking.data

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object ApiServiceProvider {
    private const val baseUrl = "https://dummyjson.com/"

    val client: ApiService by lazy { //only creates variable when called
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
    @GET("recipes")
    suspend fun fetchAllRecipes(): AllRecipesSearchResponse
    @GET("recipes/meal-type/{mealType}")
    suspend fun fetchRecipesByMeal(
        //@Query(value = "meal-type") mealType: String
        @Path(value = "mealType") mealType: String
    ): AllRecipesSearchResponse
}