package com.example.myapplication.Models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val weight: Int,
    val image: String,
    val article: Int
)

