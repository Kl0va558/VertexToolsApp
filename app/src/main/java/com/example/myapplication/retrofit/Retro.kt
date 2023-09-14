package com.example.myapplication.retrofit

import com.example.myapplication.Models.Product
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.http.GET

interface Retro {
    @GET("products")
    suspend fun getProductsList(): List<Product>

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://example.com/")
            .addConverterFactory(
                Json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()))
            .build()
    }
}

