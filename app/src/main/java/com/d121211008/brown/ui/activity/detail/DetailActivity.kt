package com.d121211008.brown.ui.activity.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211008.brown.R
import com.d121211008.brown.data.model.Brown
import com.d121211008.brown.ui.theme.BrownThemePhotoTheme

class DetailActivity : ComponentActivity() {

    private var selectedBrown: Brown? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        selectedBrown = intent.getParcelableExtra("Brown Theme Photo")
        setContent {
            BrownThemePhotoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onSecondary
                ) {
                    DetailsScreen()
                }
            }
        }
    }

    @Composable
    fun DetailsScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(selectedBrown?.largeImageURL)
                    .crossfade(true)
                    .build(),
                contentDescription = selectedBrown?.tags,
                modifier = Modifier
                    .padding(16.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .width(400.dp)
                    .height(500.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,

                )

            //name
            Text(text ="Tags : ${selectedBrown?.tags.toString()}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold )
            Spacer(modifier = Modifier.height(8.dp))

            //likes
            Row {
                Image(painter = painterResource(id = R.drawable.baseline_thumb_up_24), contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = selectedBrown?.likes.toString())
            }
            Spacer(modifier = Modifier.height(8.dp))

            //views
            Row{
                Image(painter = painterResource(id = R.drawable.baseline_remove_red_eye_24), contentDescription = null )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = selectedBrown?.views.toString())
            }
            Spacer(modifier = Modifier.height(8.dp))

            //downloads
            Row{
                Image(painter = painterResource(id = R.drawable.baseline_file_download_24), contentDescription = null )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = selectedBrown?.downloads.toString())
            }
        }
    }
}
