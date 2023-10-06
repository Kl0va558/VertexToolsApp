package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.myapplication.models.Product
import com.example.myapplication.retrofit.Retro

@Composable
fun CategoryScreen(
    navController: NavController
) {
    val products = remember {
        mutableStateOf<List<Product>>(emptyList())
    }

    LaunchedEffect(key1 = Unit) {
        val result = Retro.api.getProductsList()
        products.value = result
    }

    var text by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(value = text, onValueChange =
        {
            text = it
        }, label = { Text("Поиск") })

        LazyColumn {
            items(products.value.filter {
                it.name.contains(text)
            }) {
                ProductCard(product = it, false, navController = navController)
            }
        }
    }
}