package com.example.myapplication.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val category_id: Int,
    val description: String,
    val image: String,
    val weight: Int,
    val article: String,
    val nmid: String
)

