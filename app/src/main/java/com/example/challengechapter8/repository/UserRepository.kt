package com.example.challengechapter8.repository

import androidx.compose.runtime.MutableState
import com.example.challengechapter8.data.api.MovieAPI
import com.example.challengechapter8.data.response.GetAllUserResponse
import com.example.challengechapter8.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class UserRepository @Inject constructor(@Named("user") private val apiService: MovieAPI) {
    fun loginRepo(email : String, password : String, liveData : MutableState<GetAllUserResponse>)  {

        val call : Call<GetAllUserResponse> = apiService.loginUser(email, password)
        call.enqueue(object  : Callback<GetAllUserResponse> {
            override fun onResponse(
                call: Call<GetAllUserResponse>,
                response: Response<GetAllUserResponse>
            )  {
                if (response.isSuccessful){
                    if (response.code().toString() == "404"){
                        liveData.value = GetAllUserResponse("","","","","","","")
                    }else{
                        liveData.value = response.body()!!
                    }
                }else{
                    liveData.value = GetAllUserResponse("","","","","","","")
                }
            }
            override fun onFailure(call: Call<GetAllUserResponse>, t: Throwable) {
                liveData.value = GetAllUserResponse("","","","","","","")
            }
        })
    }

    fun registerRepo(username : String, email :String, password: String){
        val call : Call<RegisterResponse> = apiService.registerUser(email, password, username)
        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){

                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}