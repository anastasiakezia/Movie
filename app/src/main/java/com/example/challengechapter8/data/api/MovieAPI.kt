package com.example.challengechapter8.data.api

import com.example.challengechapter8.data.response.GetAllMovieResponse
import com.example.challengechapter8.data.response.GetAllUserResponse
import com.example.challengechapter8.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface MovieAPI {
    //Movie Endpoint
    @GET("/3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String
    ): GetAllMovieResponse

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") api_key: String
    ): GetAllMovieResponse

    //Auth Endpoint
    @POST("register.php/")
    @FormUrlEncoded
    fun registerUser(
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("username") username :String
    ) : Call<RegisterResponse>

    @POST("login.php")
    @FormUrlEncoded
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<GetAllUserResponse>

}