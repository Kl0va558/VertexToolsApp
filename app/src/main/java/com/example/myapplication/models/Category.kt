package com.example.myapplication.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val article: Int,
    val name: String,
)
