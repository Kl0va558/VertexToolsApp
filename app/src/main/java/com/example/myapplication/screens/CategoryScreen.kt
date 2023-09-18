package com.example.myapplication.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.example.myapplication.Models.Product
import com.example.myapplication.retrofit.Retro
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(
    onNavigateToFriends: () -> Unit,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val products = remember {
        mutableStateOf<List<Product>>(emptyList())
    }
    Button(
        onClick = {
            coroutineScope.launch {
                val result = Retro.api.getProductsList()
                products.value = result
            }
            navController.navigate("main")
        }
    ) {
        Text(text = "Два")
    }
}