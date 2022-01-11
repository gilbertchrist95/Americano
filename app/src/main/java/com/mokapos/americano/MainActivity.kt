package com.mokapos.americano

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.mokapos.americano.data.Product
import com.mokapos.americano.ui.theme.AmericanoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.populateData()
        setContent {
            AmericanoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    tabs(viewModel)
                }
            }
        }
    }
}

@Composable
fun tabs(viewModel: MainViewModel) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Product", "Cart", "Activity")

    Column {
        TabRow(selectedTabIndex = tabIndex,) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = title) }
                )
            }
        }
        when (tabIndex) { // 6.
            0 -> productList(viewModel = viewModel)
            1 -> Text("There content")
            2 -> Text("World content")
        }
    }
}

@Composable
fun productList(viewModel: MainViewModel) {
    val products: List<Product> by viewModel.getProducts().observeAsState(listOf())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(products) { product ->
            Row(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = "https://picsum.photos/200"),
                    contentDescription = product.image,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(2.dp, Color.Gray, CircleShape)
                )
                Column(
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Text(
                        text = product.name,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = """${"$"}${product.price}""",
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}

//https://picsum.photos/200



@ExperimentalPagerApi
@Composable
fun tabWithSwiping() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf(
        "Products",
        "Cart",
        "Activity"
    )
    val pagerState = rememberPagerState()
    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions -> TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)) }
        ) {
            tabTitles.forEachIndexed{index, title ->
                Tab(
                    selected = tabIndex==index,
                    onClick = {
                        tabIndex = index

                    },
                    text = { Text(text = title) },
                )
            }
        }
        HorizontalPager(count = tabTitles.size, state = pagerState) { tabIndex ->
            Text(text = tabTitles.toString(),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true, widthDp = 480, heightDp = 640)
@Composable
fun DefaultPreview() {
    tabWithSwiping()
}