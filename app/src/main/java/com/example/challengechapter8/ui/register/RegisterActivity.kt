package com.example.challengechapter8.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.challengechapter8.theme.ChallengeChapter8Theme
import com.example.challengechapter8.ui.UserViewModel
import com.example.challengechapter8.ui.home.HomeActivity
import com.example.challengechapter8.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapter8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userViewModel: UserViewModel =
                        viewModel(modelClass = UserViewModel::class.java)
                    Greeting2(userViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting2(user: UserViewModel) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var konfirmasi by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(text = "Register", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 60.dp))
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Text(text = "Create new account to get started.", fontSize = 17.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = username,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray
            ),shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { username = it },
            label = { Text("Full Name") })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray
            ),shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { email = it },
            label = { Text("Email") })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            visualTransformation = PasswordVisualTransformation(),
            value = password,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray
            ),shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { password = it },
            label = { Text("Password") })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            visualTransformation = PasswordVisualTransformation(),
            value = konfirmasi,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray
            ),shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { konfirmasi = it },
            label = { Text("Confirm Password") })
        Spacer(modifier = Modifier.height(130.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),shape = RoundedCornerShape(15.dp), onClick = {
            if (username.isNotBlank() && password.isNotBlank()
                && email.isNotBlank() && konfirmasi.isNotBlank()) {
                if (password == konfirmasi) {
                    user.registerLive(email, password, username)
                    Toast.makeText(context, "Mantap!", Toast.LENGTH_LONG).show()
                    context.startActivity(Intent(context, LoginActivity::class.java))
                } else {
                    Toast.makeText(
                        context,
                        "Konfirmasi password tidak sesuai",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(context, "Isi semua", Toast.LENGTH_LONG).show()
            }
        }) {
            Text(text = "Register", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ChallengeChapter8Theme {
//        Greeting2("Android")
    }
}