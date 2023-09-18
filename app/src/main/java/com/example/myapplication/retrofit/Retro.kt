package com.example.myapplication.retrofit

import com.example.myapplication.Models.Product
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface Retro {
    @GET("products")
    suspend fun getProductsList(): List<Product>

    @GET("products")
    suspend fun getProductsByCategory(@Query("category") category:String): List<Product>

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(
                Json.asConverterFactory(
                    MediaType.get("application/json")
                )
            )
            .build()
        val api = retrofit.create(Retro::class.java)
    }
}

