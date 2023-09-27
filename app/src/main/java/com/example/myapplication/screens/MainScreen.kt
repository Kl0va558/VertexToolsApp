package com.example.myapplication.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Models.Product
import com.example.myapplication.retrofit.Retro

@Composable
fun MainScreen(
    onNavigateToFriends: () -> Unit,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val products = remember {
        mutableStateOf<List<Product>>(emptyList())
    }
    LaunchedEffect(key1 = Unit) {
        val result = Retro.api.getProductsList()
        products.value = result
    }
    ScrollContent(innerPadding = PaddingValues(), products.value)
}