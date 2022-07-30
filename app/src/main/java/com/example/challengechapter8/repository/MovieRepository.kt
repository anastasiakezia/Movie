package com.example.challengechapter8.repository

import com.example.challengechapter8.data.api.MovieAPI
import com.example.challengechapter8.data.response.GetAllMovieResponse
import javax.inject.Inject
import javax.inject.Named

class MovieRepository @Inject constructor(@Named("movie") private val apiService: MovieAPI) {

    suspend fun getFilm(): GetAllMovieResponse {
        return apiService.getPopularMovie("4861681ff7acfad2199c40775e4ef5cb")
    }

    suspend fun getFilmNowPlaying(): GetAllMovieResponse {
        return apiService.getNowPlayingMovie("4861681ff7acfad2199c40775e4ef5cb")
    }
}