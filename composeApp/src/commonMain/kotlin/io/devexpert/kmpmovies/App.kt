package io.devexpert.kmpmovies

import androidx.compose.runtime.Composable
import io.devexpert.kmpmovies.data.database.MoviesDao
import io.devexpert.kmpmovies.ui.screens.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(moviesDao: MoviesDao) {
    Navigation(moviesDao)
}