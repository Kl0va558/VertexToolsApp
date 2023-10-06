package com.example.myapplication.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.myapplication.models.Product
import com.example.myapplication.retrofit.Retro

@Composable
fun DetailScreen(
    productId: Int?,
    navController: NavController
    ) {
    val products = remember {
        mutableStateOf<List<Product>>(emptyList())
    }

    LaunchedEffect(key1 = Unit) {
        val result = Retro.api.getProductById(productId!!)
        products.value = result
    }

    LazyColumn {
        items(products.value) {
            BigProductCard(it)
        }
    }
}