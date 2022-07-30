package com.example.challengechapter8.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.challengechapter8.R
import com.example.challengechapter8.data.response.GetAllUserResponse
import com.example.challengechapter8.datastore.UserDatastoreManager
import com.example.challengechapter8.theme.ChallengeChapter8Theme
import com.example.challengechapter8.ui.UserViewModel
import com.example.challengechapter8.ui.home.HomeActivity
import com.example.challengechapter8.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapter8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userViewModel : UserViewModel = viewModel(modelClass = UserViewModel::class.java)
                    val hasil = userViewModel.userState.value
                    Greeting(hasil, userViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(hasil: GetAllUserResponse, userViewModel: UserViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val userManager = UserDatastoreManager(context = context)
    val scope = rememberCoroutineScope()
    userViewModel.loginLive(email, password)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)) {
        Text(text = "Hello Again!", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier
            .padding(top = 60.dp))
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Text(text = "Welcome Back, you've been missed!", fontSize = 17.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
        Spacer(modifier = Modifier.padding(top = 30.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Gray
        ),shape = RoundedCornerShape(15.dp),value = email, onValueChange = { email = it },label = { Text("Email") })
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(13.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Gray
        ),shape = RoundedCornerShape(15.dp),value = password, onValueChange = { password = it },label = { Text(" Password") },visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(250.dp))
        Text(textAlign = TextAlign.Center,color = Color.Gray ,fontWeight = FontWeight.Medium, text = "Belum punya akun?",modifier = Modifier
            .fillMaxWidth()
            .clickable {
                context.startActivity(Intent(context, RegisterActivity::class.java))
            })
        Spacer(modifier = Modifier.padding(bottom = 20.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = White),shape = RoundedCornerShape(15.dp),onClick = {
            scope.launch {
                if (hasil.id.isBlank()){
                    Toast.makeText(context, "USERNAME ATAU PASSWORD SALAH", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context, "Masok!", Toast.LENGTH_LONG).show()
                    context.startActivity(Intent(context, HomeActivity::class.java))
                    userManager.setStatus("yes")
                    userManager.login(hasil.username, hasil.password, hasil.email, hasil.id)
                }
            }
        }) {
            Text(text = "Login", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChallengeChapter8Theme {
        //Greeting("Android")
    }
}