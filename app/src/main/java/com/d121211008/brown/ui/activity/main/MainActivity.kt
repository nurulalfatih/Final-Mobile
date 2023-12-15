package com.d121211008.brown.ui.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211008.brown.data.model.Brown
import com.d121211008.brown.ui.activity.detail.DetailActivity
import com.d121211008.brown.ui.theme.BrownThemePhotoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrownThemePhotoTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondary
                ) {
                    Column {
                        CenterAlignedTopAppBar(
                            modifier = Modifier.background(Color.Black),
                            title = {
                                Text(
                                    text ="Brown Theme Photo",
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        )
                        val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                        ListBrownScreen(mainViewModel.mainUiState)
                    }
                }
            }
        }
    }
    //
    @Composable
    private fun ListBrownScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            when(mainUiState) {
                is MainUiState.Success -> ListBrown(mainUiState.brown)
                is MainUiState.Error -> Text(text = "Oops There's Something Wrong", fontSize = 16.sp)
                is MainUiState.Loading ->  Text(text = "Wait A Moment", fontSize = 16.sp)
            }
        }
    }

    @Composable
    fun ListBrown(brown: List<Brown>, modifier: Modifier = Modifier) {
        LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(2)) {
            items(brown) { brown->
                BrownCard(brown = brown)
            }
        }
    }
    @Composable
    fun BrownCard(brown: Brown) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                .height(280.dp)
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("Brown Theme Photo", brown)
                    startActivity(intent)
                }) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)){
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(brown.webformatURL)
                        .crossfade(true)
                        .build(),
                    contentDescription = brown.tags,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium)

                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "From: ${brown.user.toString()}")
            }
        }
    }

}