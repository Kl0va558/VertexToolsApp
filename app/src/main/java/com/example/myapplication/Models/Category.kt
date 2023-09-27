package com.example.myapplication.Models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val article: Int,
    val name: String,
)
