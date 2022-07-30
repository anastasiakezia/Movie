package com.example.challengechapter8.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter8.data.response.Result
import com.example.challengechapter8.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    private val filmState = MutableStateFlow(emptyList<Result>())
    private val filmState2 = MutableStateFlow(emptyList<Result>())

    val dataState : StateFlow<List<Result>>
        get() = filmState

    init {
        viewModelScope.launch {
            val film = repository.getFilm()
            filmState.value = film.results
        }
    }

    val dataState2 : StateFlow<List<Result>>
        get() = filmState2

    init {
        viewModelScope.launch {
            val film = repository.getFilmNowPlaying()
            filmState2.value = film.results
        }
    }

}