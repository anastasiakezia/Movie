package com.example.challengechapter8.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.challengechapter8.R
import com.example.challengechapter8.data.response.Result
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.challengechapter8.datastore.UserDatastoreManager
import com.example.challengechapter8.theme.ChallengeChapter8Theme
import com.example.challengechapter8.ui.MovieViewModel
import com.example.challengechapter8.ui.UserViewModel
import com.example.challengechapter8.ui.details.DetailsActivity
import com.example.challengechapter8.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapter8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current
                    val movieViewModel : MovieViewModel = viewModel(modelClass = MovieViewModel::class.java)
                    val userViewModel : UserViewModel = viewModel(modelClass = UserViewModel::class.java)

                    val dataFilm by movieViewModel.dataState.collectAsState()
                    val dataFilm2 by movieViewModel.dataState2.collectAsState()
                    val userManager = UserDatastoreManager(context = context)
                    val username = userViewModel.username.value
                    val scope = rememberCoroutineScope()

                    userManager.userNAME.asLiveData().observe(this@HomeActivity){
                        userViewModel.username.value = it
                    }

                    Column(
                        Modifier
                            .fillMaxWidth()) {
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Welcome back,",
                                fontSize = 15.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(start = 30.dp)
                            )
                            Text(
                                text = "Logout",
                                modifier = Modifier
                                    .padding(start = 160.dp)
                                    .clickable {
                                        scope.launch {
                                            userManager.logout()
                                            userManager.setStatus("no")
                                        }
                                        Toast
                                            .makeText(context, "Bye bye~ :(", Toast.LENGTH_LONG)
                                            .show()
                                        context.startActivity(
                                            Intent(
                                                context,
                                                LoginActivity::class.java
                                            )
                                        )
                                    },
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 3.dp))
                        Text(modifier = Modifier.padding(start = 30.dp),text = username, fontSize = 17.sp, fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.padding(top = 40.dp))
                        Text(
                            text = "Popular Movies",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 30.dp),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.ExtraBold)
                        LazyRow{
                            if (dataFilm.isEmpty()){
                                item {

                                }
                            }
                            items(dataFilm){
                                Greeting4(it)
                            }
                        }

//                        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 185.dp)){
//                            if (dataFilm.isEmpty()){
//                                item {
//
//                                }
//                            }
//                            items(dataFilm){
//                                Greeting4(it)
//                            }
//                        }
                    }
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 390.dp)
                    ) {
                        Spacer(modifier = Modifier.padding(top = 40.dp))
                        Text(
                            text = "Now Playing Movies",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 30.dp),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.ExtraBold)
                        LazyRow{
                            if (dataFilm2.isEmpty()){
                                item {

                                }
                            }
                            items(dataFilm2){
                                Greeting5(it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting4(film: Result) {
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 30.dp)
            .padding(top = 20.dp)) {
        Card(shape = RoundedCornerShape(10.dp),  elevation = 0.dp, modifier = Modifier
            .width(130.dp)
            .height(270.dp)
            .clickable {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("film", film)
                context.startActivity(intent)
            }) {
            Column(
                Modifier
                    .fillMaxSize()) {
                Image(
                    painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500/" + film.posterPath),
                    contentDescription = "gambar",
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Column(modifier = Modifier
                    .fillMaxSize()) {
                    Text(text = film.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    Spacer(modifier = Modifier.padding(top = 5.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = film.voteAverage.toString(), fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                        Spacer(modifier = Modifier.padding(start = 3.dp))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_star_24),
                            contentDescription = "gambar",
                            modifier = Modifier
                                .width(18.dp)
                                .height(18.dp)
                        )
                    }
                }
//                Column(modifier = Modifier.fillMaxSize()) {
//
//                }
            }
        }
    }
}

@Composable
fun Greeting5(movie: Result) {
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 30.dp)
            .padding(top = 20.dp)) {
        Card(shape = RoundedCornerShape(10.dp),  elevation = 0.dp, modifier = Modifier
            .width(130.dp)
            .height(270.dp)
            .clickable {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("film", movie)
                context.startActivity(intent)
            }) {
            Column(
                Modifier
                    .fillMaxSize()) {
                Image(
                    painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500/" + movie.posterPath),
                    contentDescription = "gambar",
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Column(modifier = Modifier
                    .fillMaxSize()) {
                    Text(text = movie.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    Spacer(modifier = Modifier.padding(top = 5.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = movie.voteAverage.toString(), fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                        Spacer(modifier = Modifier.padding(start = 3.dp))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_star_24),
                            contentDescription = "gambar",
                            modifier = Modifier
                                .width(18.dp)
                                .height(18.dp)
                        )
                    }
                }
//                Column(modifier = Modifier.fillMaxSize()) {
//
//                }
            }
        }
    }
}