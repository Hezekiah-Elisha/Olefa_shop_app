package com.hub.olefashopapp.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hub.olefashopapp.R
import com.hub.olefashopapp.model.Product
import com.hub.olefashopapp.model.ProductItem
import com.hub.olefashopapp.model.Rating
import com.hub.olefashopapp.ui.theme.manrope
import com.hub.olefashopapp.utils.Constants

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    when (val state = viewModel.uiState) {
        is FakeStoreUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxWidth())
        is FakeStoreUiState.Success -> ProductsGridScreen(state.products, modifier)
        is FakeStoreUiState.Error -> ErrorScreen({ viewModel.getProducts() }, modifier = modifier.fillMaxWidth())
    }
}

@Composable
fun ProductsGridScreen(
    products: Product,
    modifier: Modifier = Modifier
) {
    Column {
        TopBar()
        Spacer(modifier = Modifier.height(16.dp))
        SearchSection()
        CategoryList(category = Constants.categories)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = modifier
        ) {
            items(products.size) { index ->
                ProductCard(products[index])
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.discover),
                fontSize = 30.sp,
                fontFamily = manrope,
                fontWeight = FontWeight.ExtraBold
            )
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon (
                    painter = painterResource(id = R.drawable.baseline_shopping_basket_24),
                    contentDescription = stringResource(R.string.cart)
                )
            }
        },
    )
}

@Composable
fun SearchSection(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp) // Add this line
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){


            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .width(290.dp)
                    .height(50.dp)
                    .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = null
                )
                Text(
                    text = "Search anything",
                    modifier = Modifier
                        .padding(8.dp)
                        .width(200.dp),
                    color = Color.Gray,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Medium
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(4.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_filter_list_24),
                    contentDescription = stringResource(R.string.filter),
                    modifier = modifier.size(29.dp),
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}


@Composable
fun ProductCard(product: ProductItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(8.dp)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.image)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.product_photo),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp),
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
        )
        Column (
            modifier = modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = product.title,
                fontFamily = manrope,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Kes. ${product.price * 100}",
                fontFamily = manrope,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        }
    }
}

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    category: List<String> = listOf("Category"),
) {
    LazyRow(
        modifier = modifier.padding(start = 16.dp, end = 16.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(category.size) { index ->
            CategoryChip(text = category[index])
        }
    }
}

@Composable
fun CategoryChip(text: String = "Category") {
    SuggestionChip(
        onClick = { Log.d("Suggestion chip", "hello world") },
        label = { Text(text = text) }
    )
}


@Composable
fun LoadingScreen() {
    Text(text ="Loading...")
}

@Composable
fun ErrorScreen(retryAction: () -> Unit , modifier: Modifier = Modifier) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(text = "Error loading products")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Composable
fun TAppPreview() {
    CategoryList(category = Constants.categories)
}

