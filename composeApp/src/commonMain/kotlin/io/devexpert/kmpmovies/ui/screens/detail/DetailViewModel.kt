package io.devexpert.kmpmovies.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devexpert.kmpmovies.data.Movie
import io.devexpert.kmpmovies.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailViewModel(private val id: Int) : ViewModel(), KoinComponent {
    private val repository: MoviesRepository by inject()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null
    )

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.fetchMovieById(id).collect {
                it?.let { _state.value = UiState(loading = false, movie = it) }
            }
        }
    }

    fun onFavoriteClick() {
        _state.value.movie?.let {
            viewModelScope.launch {
                repository.toggleFavorite(it)
            }
        }
    }
}