package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.screens.CategoryScreen
import com.example.myapplication.screens.CenterAlignedTopAppBarExample
import com.example.myapplication.screens.DetailScreen
import com.example.myapplication.screens.MainScreen
import com.example.myapplication.screens.WildberriesScreen
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
                    navController
                )
            }
        }
        composable("details/{productId}", arguments = listOf(navArgument("productId") {
            type = NavType.IntType
        })) { backStackEntry ->
            CenterAlignedTopAppBarExample(title = "Подробности", navController = navController) {
                DetailScreen(
                    productId = backStackEntry.arguments?.getInt("productId"),
                    navController
                )
            }
        }
        composable("wildberries") {
            CenterAlignedTopAppBarExample(
                title = "Товары WB",
                navController = navController
            ) {
                WildberriesScreen(navController = navController)
            }
        }
    }
}