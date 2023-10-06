package com.example.myapplication.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.models.Product
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBarExample(
    title: String,
    navController: NavController,
    content: @Composable () -> Unit
) {
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(
                        modifier = Modifier.size(width = 240.dp, height = 50.dp),
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                navController.navigate("main")
                            }
                        }) {
                        Icon(
                            modifier = Modifier.weight(0.25f),
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Домашняя страница"
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "Главная",
                            fontSize = 26.sp,
                            textAlign = TextAlign.Start
                        )
                    }
                    TextButton(
                        modifier = Modifier.size(width = 240.dp, height = 50.dp),
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                navController.navigate("category")
                            }
                        }) {
                        Icon(
                            modifier = Modifier.weight(0.25f),
                            imageVector = Icons.Filled.List,
                            contentDescription = "Категории"
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(modifier = Modifier.weight(1f), text = "Категории", fontSize = 26.sp)
                    }
                    TextButton(modifier = Modifier.size(width = 240.dp, height = 50.dp),
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                navController.navigate("wildberries")
                            }
                        }) {
                        Icon(
                            modifier = Modifier.weight(0.25f),
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Wildberries"
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(modifier = Modifier.weight(1f), text = "Wildberries", fontSize = 26.sp)
                    }
                }
            }
        },
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            }, content = {
                Box(modifier = Modifier.padding(it)) {
                    content()
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollContent(
    innerPadding: PaddingValues,
    product: List<Product>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var count = 0
        items(product) { products ->
            if (product.size > 1 && navController.currentDestination!!.route == "main" && count < 1) {
                count += 1
                Row(modifier = Modifier
                    .clickable {
                        navController.navigate("details/${products.id}")
                    }
                ) {
                    val pagerState = rememberPagerState(pageCount = {
                        10
                    })
                    Column(modifier = Modifier.fillMaxSize()) {
                        HorizontalPager(state = pagerState, beyondBoundsPageCount = 10) {
                            AsyncImage(
                                model = products.image,
                                contentDescription = "Картинка"
                            )
                        }
                        Text(text = products.name)
                    }
                }

            } else if (product.size == 1) {
                Row(modifier = Modifier.padding(8.dp)) {
                    AsyncImage(
                        model = products.image, contentDescription = "Image of instrument",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(
                                RectangleShape
                            )
                    )
                    val uriHandler = LocalUriHandler.current
                    Column {
                        Text(text = products.name)
                        Text(text = products.description)
                        if (products.nmid != "#Н/Д") {
                            Button(onClick = {
                                uriHandler.openUri("https://www.wildberries.ru/catalog/${products.nmid}/detail.aspx")
                            }) {
                                Text(text = "Wildberries")
                            }
                        }
                    }
                }
            } else if (navController.currentDestination!!.route != "main") {
                Row(modifier = Modifier
                    .clickable {
                        navController.navigate("details/${products.id}")
                    }
                ) {
                    AsyncImage(
                        model = products.image, contentDescription = "Image of instrument",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(
                                RectangleShape
                            )
                    )
                    Text(text = products.name)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductCard(product: Product, isImagePager: Boolean, navController: NavController) {
    if (!isImagePager) {
        Row(modifier = Modifier
            .clickable {
                navController.navigate("details/${product.id}")
            }
        ) {
            AsyncImage(
                model = product.image, contentDescription = "Image of instrument",
                modifier = Modifier
                    .size(40.dp)
                    .clip(
                        RectangleShape
                    )
            )
            Text(text = product.name)
        }
    } else {
        Row(modifier = Modifier
            .clickable {
                navController.navigate("details/${product.id}")
            }
        ) {

        }
    }
}

@Composable
fun BigProductCard(product: Product){
    Row(modifier = Modifier.padding(8.dp)) {
        AsyncImage(
            model = product.image, contentDescription = "Image of instrument",
            modifier = Modifier
                .size(150.dp)
                .clip(
                    RectangleShape
                )
        )
        val uriHandler = LocalUriHandler.current
        Column {
            Text(text = product.name)
            Text(text = product.description)
            if (product.nmid != "#Н/Д") {
                Button(onClick = {
                    uriHandler.openUri("https://www.wildberries.ru/catalog/${product.nmid}/detail.aspx")
                }) {
                    Text(text = "Wildberries")
                }
            }
        }
    }
}