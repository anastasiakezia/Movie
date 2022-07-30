package com.example.challengechapter8.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import com.example.challengechapter8.R
import com.example.challengechapter8.datastore.UserDatastoreManager
import com.example.challengechapter8.theme.ChallengeChapter8Theme
import com.example.challengechapter8.ui.home.HomeActivity
import com.example.challengechapter8.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapter8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting3("Android")
                }
            }
        }
        val userManager = UserDatastoreManager(this)
        Handler().postDelayed({
            userManager.userSTATUS.asLiveData().observe(this){
                if (it == "yes"){
                    runOnUiThread{
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                }else{
                    runOnUiThread{
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                }
            }

        }, 3000)
    }
}

@Composable
fun Greeting3(name: String) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.splash_img), contentDescription = "gambar", modifier = Modifier.height(100.dp).width(100.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ChallengeChapter8Theme {
        Greeting3("Android")
    }
}