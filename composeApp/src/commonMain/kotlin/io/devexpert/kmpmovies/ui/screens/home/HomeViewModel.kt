package io.devexpert.kmpmovies.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devexpert.kmpmovies.data.Movie
import io.devexpert.kmpmovies.data.MoviesService
import io.devexpert.kmpmovies.data.RemoteMovie
import kotlinx.coroutines.launch

class HomeViewModel(private val moviesService: MoviesService) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(
                loading = false,
                movies = moviesService.fetchPopularMovies().results.map { it.toDomainMovie() }
            )
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}

private fun RemoteMovie.toDomainMovie(): Movie = Movie(
    id = id,
    title = title,
    poster = "https://image.tmdb.org/t/p/w185/$posterPath"
)
