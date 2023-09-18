package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Models.Product
import com.example.myapplication.screens.CategoryScreen
import com.example.myapplication.screens.CenterAlignedTopAppBarExample
import com.example.myapplication.screens.MainScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme(darkTheme = true) {
                MyAppNavHost()
            }
        }
    }
}

//@Composable
//fun test() {
//    /*
//                    Это то, с помощью чего ты можешь совершать ассинхронные операции. Все
//                    асинхронные операции, которые ты запускаешь с помощью этого скоупа будут
//                    привязаны к жизненному циклу компоуз функции. Если компоуз функция перестает
//                    существовать (например пользователь ушел на другой экран), то все асинхронные
//                    операции в этом скоупе отменятся.
//                     */
//    val coroutineScope = rememberCoroutineScope()
//    /*
//    Это специальлное состояние для хранения информации в экране. Компоуз может
//    отслеживать его изменения и когда оно изменяется, компоуз перестраивает экран под
//    новые данные в стейте
//     */
//    val products = remember {
//        mutableStateOf<List<Product>>(emptyList())
//    }
//
//    LazyColumn {
//        items(products.value) {
//            Text(text = it.name)
//        }
//    }
//}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "main"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("main") {
            CenterAlignedTopAppBarExample("Главная")
            MainScreen(
                onNavigateToFriends = { navController.navigate("main") },
                navController
            )
        }
        composable("category") {
            CenterAlignedTopAppBarExample("Категория")
            CategoryScreen(
                onNavigateToFriends = { navController.navigate("category") },
                navController
            )
        }
    }
}