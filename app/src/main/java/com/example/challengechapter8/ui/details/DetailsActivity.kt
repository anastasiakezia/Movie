package com.example.challengechapter8.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.challengechapter8.data.response.Result
import com.example.challengechapter8.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.challengechapter8.theme.ChallengeChapter8Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapter8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val data = intent.getParcelableExtra<Result>("film") as Result
                    val onClick = {}
                    Box(modifier = Modifier.fillMaxSize()){
                        Greeting5(film = data)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting5(film: Result) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .padding(end = 20.dp)
                .padding(top = 20.dp)
                .height(160.dp)) {
            Image(painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500/" + film.posterPath),
                contentDescription = "gambar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(15.dp))
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(15.dp)) {
            Text(
                text = film.originalTitle,fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 10.dp)
            )

            Text(
                text = "Synopsis",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(bottom = 5.dp)
            )
            Text(
                text = film.overview,
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = "Vote Average",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 5.dp)
            )
            Text(
                text = film.voteAverage.toString(),
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = "Vote Count",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 5.dp)
            )
            Text(
                text = film.voteCount.toString(),
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = "Release Date",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 5.dp)
            )
            Text(
                text = film.releaseDate.toString(),
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ChallengeChapter8Theme {
//        Greeting5("Android")
    }
}