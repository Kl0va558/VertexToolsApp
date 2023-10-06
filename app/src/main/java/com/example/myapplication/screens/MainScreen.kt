package com.example.myapplication.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.models.Product
import com.example.myapplication.retrofit.Retro

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    onNavigateToFriends: () -> Unit,
    navController: NavController
) {

    val products = remember {
        mutableStateOf<List<Product>>(emptyList())
    }

    LaunchedEffect(key1 = Unit) {
        val result = Retro.api.getProductsList()
        products.value = result
    }

    val taken = products.value.take(10)
    val pagerState = rememberPagerState(pageCount = {
        taken.size
    })
    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState) {
            Column {
                AsyncImage(
                    model = taken[it].image,
                    contentDescription = "Картинка"
                )
                Text(text = taken[it].name)
            }
        }

        LazyColumn {
            items(products.value) {
                ProductCard(
                    product = it,
                    isImagePager = false,
                    navController = navController
                )
            }
        }
    }
}