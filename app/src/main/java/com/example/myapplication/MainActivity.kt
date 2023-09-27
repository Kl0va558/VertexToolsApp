package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Models.Product
import com.example.myapplication.retrofit.Retro
import com.example.myapplication.screens.CategoryScreen
import com.example.myapplication.screens.CenterAlignedTopAppBarExample
import com.example.myapplication.screens.MainScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch
import org.w3c.dom.Text

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
            CenterAlignedTopAppBarExample("Главная", navController) {
                MainScreen(
                    onNavigateToFriends = { navController.navigate("main") },
                    navController
                )
            }
        }
        composable("category") {
            CenterAlignedTopAppBarExample("Категория", navController) {
                CategoryScreen(
                    onNavigateToFriends = { navController.navigate("category") },
                    navController
                )
            }
        }
    }
}