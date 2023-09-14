package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.Models.Product
import com.example.myapplication.retrofit.Retro
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme(darkTheme = false) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    test()
                }
            }

        }
    }
}

@Composable
fun test(){
    /*
                    Это то, с помощью чего ты можешь совершать ассинхронные операции. Все
                    асинхронные операции, которые ты запускаешь с помощью этого скоупа будут
                    привязаны к жизненному циклу компоуз функции. Если компоуз функция перестает
                    существовать (например пользователь ушел на другой экран), то все асинхронные
                    операции в этом скоупе отменятся.
                     */
    val coroutineScope = rememberCoroutineScope()
    /*
    Это специальлное состояние для хранения информации в экране. Компоуз может
    отслеживать его изменения и когда оно изменяется, компоуз перестраивает экран под
    новые данные в стейте
     */
    val products = remember {
        mutableStateOf<List<Product>>(emptyList())
    }

    /*
    Если ты хочешь, чтобы экран запрашивал сам продукты, без действий от пользователя
    то разкомментируй этот блок. Это блок кода, который при создании компоуз функции асинхронно
    вызовется один раз. Количество раз зависит от переданного ключа. Так как мы передаем
    неизменяемый ключ, вызов будет только один.
     */
//                    LaunchedEffect(key1 = Unit){
//                        val result = Retro.api.getProductsList()
//                        products.value = result
//                    }

    LazyColumn{
        items(products.value){
            Text(text = it.name)
        }
    }
    Button(
        onClick = {
            /*
            Тут мы запускаем в другом потоке операцию получения продуктов и
            присваеваем результат в наш стейт
             */
            coroutineScope.launch {
                val result = Retro.api.getProductsList()
                products.value = result
            }
        }
    ) {
        Text(text = "Получить продукты")
    }
}