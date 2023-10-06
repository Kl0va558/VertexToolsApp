package com.example.myapplication.retrofit

import com.example.myapplication.models.Product
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface Retro {
    @GET("products")
    suspend fun getProductsList(): List<Product>

    @GET("product")
    suspend fun getProductById(@Query("productId") productId: Int): List<Product>

    @GET("products")
    suspend fun getProductsByCategory(@Query("category") category:String): List<Product>

    @GET("productsWithNmId")
    suspend fun getProductsByNmId(): List<Product>

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("http://151.248.121.24:8080/")
            .addConverterFactory(
                Json.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
        val api = retrofit.create(Retro::class.java)
    }
}

