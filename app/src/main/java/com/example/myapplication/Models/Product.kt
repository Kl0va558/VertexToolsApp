package com.example.myapplication.Models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val category_id: Int,
    val description: String,
    val image: String,
    val weight: Int,
)

